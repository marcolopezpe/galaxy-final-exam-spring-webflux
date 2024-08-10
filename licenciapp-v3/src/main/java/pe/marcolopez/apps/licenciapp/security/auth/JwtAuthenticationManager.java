package pe.marcolopez.apps.licenciapp.security.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pe.marcolopez.apps.licenciapp.commons.exceptions.web.TokenNotFoundException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    return Mono.just(authentication)
        .map(auth -> jwtTokenProvider.getClaims(auth.getCredentials().toString()))
        .log()
        .onErrorResume(e -> Mono.error(new TokenNotFoundException("Token incorrecto")))
        .map(claims -> new UsernamePasswordAuthenticationToken(
            claims.getSubject(),
            null,
            Stream.of(claims.get("authorities"))
                .filter(List.class::isInstance)
                .map(role -> (List<Map<String, String>>) role)
                .flatMap(List::stream)
                .map(r -> r.get("authority"))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList())
        ));
  }
}
