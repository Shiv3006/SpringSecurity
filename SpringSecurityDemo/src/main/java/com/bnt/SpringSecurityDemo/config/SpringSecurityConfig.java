package com.bnt.SpringSecurityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig {

	@Bean
	public UserDetailsService user(PasswordEncoder encoder) {
		UserDetails user = User.withUsername("shiv")
				               .password(encoder.encode("shiv"))
				               .roles("USER").build();

		UserDetails admin = User.withUsername("shivanjali")
				                .password(encoder.encode("shivanjali")).roles("ADMIN")
				                .build();

		return new InMemoryUserDetailsManager(user, admin);

	}

	@Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception {
		return http.csrf().disable().
				   authorizeHttpRequests()
				   . antMatchers("/hello/home").permitAll()
				   . and()
				   .authorizeHttpRequests().antMatchers("/hello/**").authenticated()
				   .and().formLogin()
				   .and().build();

	}

	@Bean
	public PasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}

}
