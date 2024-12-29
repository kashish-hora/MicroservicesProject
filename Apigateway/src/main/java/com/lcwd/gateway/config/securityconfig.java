package com.lcwd.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableWebFluxSecurity
@Configuration
public class securityconfig {

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange()
//                .anyExchange()
//                .authenticated()
//                .and()
//                .oauth2Client()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//
//        return http.build();
//    }


    @Configuration
    @EnableWebFluxSecurity
    public class SecurityConfig {

        @Bean
        public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
            return http
                    .authorizeExchange(exchange -> exchange
                            .anyExchange().authenticated() // Use anyExchange() for WebFlux
                    )
                    .oauth2Client(withDefaults()) // Configure OAuth2 client
                    .oauth2ResourceServer(oauth2 -> oauth2
                            .jwt(withDefaults()) // JWT configuration for OAuth2 resource server
                    )
                    .build();
        }
    }


}
