package com.todoList.todoList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.todoList.todoList.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SegurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private AuthenticationProvider authProvider;
	
	@Bean	// Este es el filtro que permite determinar cuales endpoints son públicos y privados, también la redirección
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf ->
						csrf
						.disable())
				.authorizeHttpRequests(authRequest ->
				authRequest
					.requestMatchers(HttpMethod.GET).permitAll()
					.requestMatchers(HttpMethod.OPTIONS).permitAll()
					.requestMatchers("/auth/*").permitAll()
					.anyRequest().authenticated()
					)
				.sessionManagement(sessionManager ->
				sessionManager
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				//.formLogin(withDefaults())
				.build();
	}
	
	
}
