//package com.os.service.core.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final JwtConverter jwtConverter;
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(csrf -> csrf.disable())  // Desabilita a proteção CSRF (não recomendado para produção sem cuidado)
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().permitAll())  // Exige autenticação para qualquer requisição
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwtConfigurer -> jwtConfigurer
//                                .jwtAuthenticationConverter(jwtConverter)))  // Configura o Resource Server para validar JWTs
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        return http.build();
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/realms/Energ/protocol/openid-connect/certs").build();
//    }
//}