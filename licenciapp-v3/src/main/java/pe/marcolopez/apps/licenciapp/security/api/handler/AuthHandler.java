package pe.marcolopez.apps.licenciapp.security.api.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.marcolopez.apps.licenciapp.security.dto.LoginRequestDto;
import pe.marcolopez.apps.licenciapp.security.dto.LoginResponseDto;
import pe.marcolopez.apps.licenciapp.security.service.UsuarioService;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthHandler {

  private final UsuarioService usuarioService;

  public Mono<ServerResponse> login(ServerRequest request) {
    return request.bodyToMono(LoginRequestDto.class)
        .flatMap(loginDto -> ServerResponse.ok()
            .body(usuarioService.login(loginDto), LoginResponseDto.class));
  }
}
