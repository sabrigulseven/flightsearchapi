package com.sabrigulseven.flight.config;


import com.sabrigulseven.flight.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/auth/**",
                        "/swagger-ui.html",
                        "swagger-ui/index.html",
                        "/swagger-resources/**",
                        "/swagger-resources",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/configuration/**",
                        "/webjars/**",
                        "/swagger-ui/**",
                        "/h2-console")
                .permitAll()
                .requestMatchers("/api/v1/admin/**").hasRole(UserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
