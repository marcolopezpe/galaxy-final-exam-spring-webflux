package pe.marcolopez.apps.licenciapp.security.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static java.util.Objects.isNull;

@Component
public class JwtFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    ServerHttpRequest req = exchange.getRequest();
    String path = req.getPath().value();

    if (path.contains("auth") // AUTHENTICATION
        || path.contains("public") // PUBLIC RESOURCES
        || path.contains("swagger") // SWAGGER-UI
        || path.contains("api-docs")) { // API-DOCS
      return chain.filter(exchange);
    }

    String auth = req.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

    if (isNull(auth)) {
      return Mono.error(new Throwable("El Token es obligatorio."));
    }

    if (!auth.startsWith("Bearer ")) {
      return Mono.error(new Throwable("Token de Autorizacion invalido."));
    }

    String token = auth.replace("Bearer ", "");
    exchange.getAttributes().put("token", token);

    return chain.filter(exchange);
  }
}
