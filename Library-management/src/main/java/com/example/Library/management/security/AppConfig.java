package com.example.Library.management.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
public class AppConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement-> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors->{
                    cors.configurationSource(new CorsConfigurationSource() {
                        @Override
                        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                            CorsConfiguration cfg=new CorsConfiguration();
                            cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
                            cfg.setAllowedMethods(Collections.singletonList("*"));
                            cfg.setAllowCredentials(true);
                            cfg.setAllowedHeaders(Collections.singletonList("*"));
                            cfg.setExposedHeaders(List.of("Authorization"));
                            return cfg;
                        }
                    });
                })
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/auth/signup","/auth/login").permitAll()
                            .requestMatchers("/librarian/**").hasRole("LIBRARIAN")
                            .requestMatchers("/student/**").hasRole("STUDENT")
                            .anyRequest().authenticated();
                }).formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());


        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
