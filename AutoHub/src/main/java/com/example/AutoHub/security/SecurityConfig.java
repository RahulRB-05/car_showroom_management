package com.example.AutoHub.security;

import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf-> csrf.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( auth-> auth
                                .requestMatchers("/auth/**").permitAll()

                                .requestMatchers("/user/**").hasRole("ADMIN")

                                .requestMatchers("/vehicle/add").hasRole("ADMIN")
                                .requestMatchers("/vehicle/update-vehicle/**").hasRole("ADMIN")
                                .requestMatchers("/vehicle/delete-vehicle/**").hasRole("ADMIN")
                                .requestMatchers("/vehicle/get-vehicle/**").hasAnyRole("ADMIN","SALES")
                                .requestMatchers("/vehicle/get-by-brand/**").hasAnyRole("ADMIN","SALES")
                                .requestMatchers("/vehicle/get-by-manufacturingyear/**").hasAnyRole("ADMIN","SALES")
                                .requestMatchers("/vehicle/get-by-vehicletype/**").hasAnyRole("ADMIN","SALES")
                                .requestMatchers("/vehicle/get-by-fueltype/**").hasAnyRole("ADMIN","SALES")
                                .requestMatchers("/vehicle/get-by-transmission/**").hasAnyRole("ADMIN","SALES")

                                .requestMatchers("/reports/**").hasAnyRole("ADMIN","SALES")

                                .requestMatchers("/sales/**").hasAnyRole("SALES")
                                .requestMatchers("/sales/getInvoice/**").hasAnyRole("ADMIN","SALES")

                                .requestMatchers("/customers/**").hasAnyRole("ADMIN","SALES")

                        .requestMatchers("/service/**").hasAnyRole("WORKER")
                        .requestMatchers("/service/serviceHistory/**").hasAnyRole("ADMIN","WORKER")

                )

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
