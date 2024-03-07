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


      http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests

    		   .requestMatchers(new AntPathRequestMatcher("/user/**")).permitAll()
   		    .requestMatchers(new AntPathRequestMatcher("/ERP_login.html")).permitAll()
   		    .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
   		    .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
   		    .requestMatchers(new AntPathRequestMatcher("/img/**")).permitAll()
   		    .requestMatchers(new AntPathRequestMatcher("/paging.html")).permitAll()
   		    .requestMatchers(new AntPathRequestMatcher("/index.html")).permitAll()
   		    .requestMatchers(new AntPathRequestMatcher("/navbar.html")).permitAll()
   		    .requestMatchers(new AntPathRequestMatcher("/error/**")).permitAll()
   		    .requestMatchers(new AntPathRequestMatcher("https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js")).permitAll()
   		    .requestMatchers(new AntPathRequestMatcher("/")).authenticated()
   		    .requestMatchers(new AntPathRequestMatcher("/PC/**")).hasAnyAuthority("ROLE_구매","ROLE_관리자")
   		    .requestMatchers(new AntPathRequestMatcher("/MG/**")).hasAnyAuthority("ROLE_구매","ROLE_관리자")
   		    .requestMatchers(new AntPathRequestMatcher("/SD/**")).hasAnyAuthority("ROLE_영업","ROLE_관리자")
   		    .requestMatchers(new AntPathRequestMatcher("/PD/**")).hasAnyAuthority("ROLE_생산","ROLE_관리자")
   		    .requestMatchers(new AntPathRequestMatcher("/AC/**")).hasAnyAuthority("ROLE_회계","ROLE_관리자")
   		    .requestMatchers(new AntPathRequestMatcher("/HR/**")).hasAnyAuthority("ROLE_인사","ROLE_관리자")
)

            .csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")).disable())

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