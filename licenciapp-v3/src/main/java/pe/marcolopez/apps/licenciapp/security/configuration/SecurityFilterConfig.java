package pe.marcolopez.apps.licenciapp.security.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.*;
import org.springframework.security.web.server.SecurityWebFilterChain;
import pe.marcolopez.apps.licenciapp.security.auth.JwtFilter;
import pe.marcolopez.apps.licenciapp.security.auth.SecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@AllArgsConstructor
public class SecurityFilterConfig {

  private final SecurityContextRepository securityContextRepository;

  @Bean
  public SecurityWebFilterChain filterChain(ServerHttpSecurity http, JwtFilter jwtFilter) {
    return http.csrf(CsrfSpec::disable)
        .authorizeExchange(
            authorizeExchangeSpec ->
                authorizeExchangeSpec
                    .pathMatchers("/v2/auth/**", "/v3/api-docs/**", "/swagger-doc/**").permitAll()
                    .anyExchange().authenticated()
        )
        .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
        .securityContextRepository(securityContextRepository)
        .httpBasic(HttpBasicSpec::disable)
        .formLogin(FormLoginSpec::disable)
        .logout(LogoutSpec::disable)
        .build();
  }
}
