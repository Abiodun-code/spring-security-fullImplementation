package com.abioduncode.spring_sec_example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.abioduncode.spring_sec_example.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private MyUserDetailsService userDetailsService;

  @Autowired
  private JWTFilter jwtFilter;
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    
    return http
      .csrf(Customizer -> Customizer.disable())
      .authorizeHttpRequests(request -> request
        .requestMatchers("register", "login").permitAll()
        .anyRequest().authenticated())
      .httpBasic(Customizer.withDefaults())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
      .build(); 
  }

  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    provider.setUserDetailsService(userDetailsService);
    return provider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
    return authenticationConfiguration.getAuthenticationManager();
  }
  
  // @Bean
  // UserDetailsService userDetailsService(){

  //   UserDetails user1 = User
  //   .withDefaultPasswordEncoder()
  //   .username("ola")
  //   .password("ola12")
  //   .roles("USER")
  //   .build();

  //   UserDetails user2 = User
  //       .withDefaultPasswordEncoder()
  //       .username("me1")
  //       .password("me12")
  //       .roles("ADMIN")
  //       .build();

  //   return new InMemoryUserDetailsManager(user1, user2);
  // }

}
