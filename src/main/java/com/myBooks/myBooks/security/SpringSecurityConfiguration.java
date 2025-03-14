package com.myBooks.myBooks.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		UserDetails userDetails1 = createNewUser("rita", "password");
		UserDetails userDetails2 = createNewUser("rita1", "password1");
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}
	
	
	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder
			= input -> passwordEncoder().encode(input);
		
		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username(username)
									.password(password)
									.roles("USER", "ADMIN")
									.build();
		return userDetails;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	
	//All URLs are protected
	//A login form is shown for unauthorized requests
	//CSRF disabled -> tipo de ataque
	//Frames
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
		
		http.formLogin(withDefaults());
		//http.csrf().disable();
		http.csrf(csrf -> csrf.disable());
		//http.headers().frameOptions().disable();
		http.headers((headers) -> headers.frameOptions(header -> header.disable()));
		
		return http.build();
	}
	
}

