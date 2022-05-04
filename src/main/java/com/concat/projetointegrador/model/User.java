package com.concat.projetointegrador.model;

import com.concat.projetointegrador.configuration.CustomAuthorityDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="\"user\"")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class User implements UserDetails {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		protected Long id;
		@NotNull
		@Column(unique = true)
		protected String username;
		@NotNull
		protected String password;
		@Pattern(regexp = "^[a-zA-Z]{1,}(?: [a-zA-Z]+){0,2}$", message = "nome deve ser composto de letras")
		@NotNull(message = "name não pode ser null")
		@Size(min = 1, max = 20, message = "name deve possuir de 1 a 20 caracteres.")
		protected String name;
		@NotNull(message = "lastName não pode ser null")
		@Size(min = 1, max = 20, message = "LastName deve possuir de 1 a 20 caracteres.")
		protected String lastName;
		protected Long cpf;

		public User(Long id, String username, String password, String name, String lastName) {
				this.id = id;
				this.username = username;
				this.password = password;
				this.name = name;
				this.lastName = lastName;
		}

		public User(String username, String password) {
				this.username = username;
				this.password = password;
		}

		public User(Long id, String username, String password, String name, String lastName, Long cpf) {
				this.id = id;
				this.username = username;
				this.password = password;
				this.name = name;
				this.lastName = lastName;
				this.cpf = cpf;
		}

		@Transient
		public String getDiscriminatorValue(){
				return this.getClass().getAnnotation(DiscriminatorValue.class).value();
		}

		@Override
		@JsonDeserialize(using = CustomAuthorityDeserializer.class)
		public Collection<? extends GrantedAuthority> getAuthorities() {
				List<SimpleGrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(getDiscriminatorValue()));

				return authorities;
		}

		@Override
		public boolean isAccountNonExpired() {
				return true;
		}

		@Override
		public boolean isAccountNonLocked() {
				return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
				return true;
		}

		@Override
		public boolean isEnabled() {
				return true;
		}

}
