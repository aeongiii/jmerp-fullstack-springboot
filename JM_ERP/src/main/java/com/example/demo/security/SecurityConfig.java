package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(authorizeHttpRequests) -> authorizeHttpRequests
						.requestMatchers(new AntPathRequestMatcher("/user/login")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/user/signup")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/bootstrapLux.css")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/error/**")).permitAll()
						.requestMatchers(new AntPathRequestMatcher(
								"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"))
						.permitAll()
						.requestMatchers(new AntPathRequestMatcher("/")).authenticated()
						.requestMatchers(new AntPathRequestMatcher("/user/**")).authenticated()
						// 전체 공개

						
						.requestMatchers(new AntPathRequestMatcher("/PC/**")).hasAnyAuthority("ROLE_구매")
						.requestMatchers(new AntPathRequestMatcher("/SD/**")).hasAnyAuthority("ROLE_영업")
						.requestMatchers(new AntPathRequestMatcher("/PD/**")).hasAnyAuthority("ROLE_생산")
						.requestMatchers(new AntPathRequestMatcher("/AC/**")).hasAnyAuthority("ROLE_회계")
						.requestMatchers(new AntPathRequestMatcher("/MG/**")).hasAnyAuthority("ROLE_재고")
						.requestMatchers(new AntPathRequestMatcher("/HR/**")).hasAnyAuthority("ROLE_인사")
						//TODO 관리자 권한 취가해야함 밥
						)

				.csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
				.headers((headers) -> headers.addHeaderWriter(
						new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
				.formLogin((formLogin) -> formLogin.loginPage("/user/login").defaultSuccessUrl("/"))

				.logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
						.logoutSuccessUrl("/").invalidateHttpSession(true));

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
