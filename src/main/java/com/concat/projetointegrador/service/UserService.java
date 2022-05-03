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
		private final BCryptPasswordEncoder passwordEncoder;

		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
				Optional<User> opt = userRepository.findByUsername(username);

				if(opt.isPresent()){
						User user = opt.get();
						return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
				}
				throw new UsernameNotFoundException("User not found!");
		}

		public User saveUser(User user) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				return userRepository.save(user);
		}


		public User getUser(String username) {
				return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		}

		public List<User> getUsers() {
				return userRepository.findAll();
		}


}