package com.nakahama.simpenbackend.Auth.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("auth/login").permitAll();
                    auth.requestMatchers("auth/test").authenticated();
                    auth.requestMatchers("auth/test-role").hasAuthority("superadmin");
                    auth.requestMatchers(HttpMethod.POST, "user").hasAuthority("superadmin");
                    auth.requestMatchers(HttpMethod.GET, "user").hasAnyAuthority("operasional", "superadmin");
                    auth.requestMatchers(HttpMethod.DELETE, "user/**").hasAuthority("superadmin");
                    auth.requestMatchers(HttpMethod.PUT, "user").hasAnyAuthority("pengajar");
                    auth.requestMatchers(HttpMethod.PUT, "user/**").hasAnyAuthority("operasional", "superadmin");

                    // TODO: set the appropriate authorities for the corresponding endpoints
                    auth.anyRequest().permitAll();
                })
                .sessionManagement(sessionAuthenticationStrategy -> sessionAuthenticationStrategy
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Autowired
    public void confAuthentication(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
