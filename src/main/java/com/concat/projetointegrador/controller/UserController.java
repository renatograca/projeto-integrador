package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.model.User;
import com.concat.projetointegrador.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
		private final UserService userService;

		@GetMapping
		public ResponseEntity<List<User>> getUsers() {
				return ResponseEntity.ok().body( userService.getUsers());
		}

		@PostMapping("/user/save")
		public ResponseEntity<User> saveUser(@RequestBody User user) {
				URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
				return ResponseEntity.created(uri).body( userService.saveUser(user));
		}

		@PostMapping("/refresh-token")
		public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
				String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
				if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
						try {
								String token = authorizationHeader.substring("Bearer ".length());
								Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
								JWTVerifier verifier = JWT.require(algorithm).build();
								DecodedJWT decodedJWT = verifier.verify(token);
								String username = decodedJWT.getSubject();

								User user = userService.getUser(username);

								String accessToken =
												JWT.create()
																.withSubject(user.getUsername())
																.withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 *1000))
																.withIssuer(request.getRequestURL().toString())
																.withClaim("role",
																		user.getAuthorities().toString()
																)
																.sign(algorithm);

								response.setHeader("access-token", accessToken);
								response.setHeader("refresh-token", token);
						} catch (Exception e) {
								log.error("login failed {}", e.getMessage());
								response.setHeader("ERROR-X", e.getMessage());
								response.sendError(HttpServletResponse.SC_FORBIDDEN);
						}
				} else {
						throw new RuntimeException("Refresh token is missing!");
				}
		}

		@Data
		class RoleToUserFrom {
				private String username;
				private String roleName;
		}

}
