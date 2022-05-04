package com.concat.projetointegrador.service;

import com.concat.projetointegrador.model.User;
import com.concat.projetointegrador.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

		private final UserRepository userRepository;

		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
				Optional<User> opt = userRepository.findByUsername(username);

				if(opt.isPresent()){
						User user = opt.get();
						return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
				}
				throw new UsernameNotFoundException("User not found!");
		}
}