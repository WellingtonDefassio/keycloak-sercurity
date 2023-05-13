package wdefassio.io.keycloaksercurity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Add httpSecurity configs");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http
//                .cors().and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/users/status/check")
                .hasRole("suporte")
                .antMatchers(HttpMethod.GET, "/hacker")
//                .hasAuthority("SCOPE_hacker")
                .hasRole("treinador")
//                .hasAnyRole("treinador", "user")
                .antMatchers(HttpMethod.GET, "/albums")
                .hasAnyRole("treinador", "user")
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);

        return http.build();
    }
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
//        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return source;
//    }

}
