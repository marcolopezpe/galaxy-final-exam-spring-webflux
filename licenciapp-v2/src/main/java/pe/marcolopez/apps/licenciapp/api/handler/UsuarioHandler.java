package pe.marcolopez.apps.licenciapp.api.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.marcolopez.apps.licenciapp.dto.UsuarioDto;
import pe.marcolopez.apps.licenciapp.service.UsuarioService;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class UsuarioHandler {

  private final Mono<ServerResponse> response404 = ServerResponse.notFound().build();
  private final Mono<ServerResponse> response400 = ServerResponse.badRequest().build();
  private final UsuarioService usuarioService;

  // GET ALL
  public Mono<ServerResponse> getAllUsuarios(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(usuarioService.findAll(), UsuarioDto.class);
  }

  // GET ALL BY FILTER
  public Mono<ServerResponse> getAllByFilter(ServerRequest request) {
    Optional<String> nombreUsuario = request.queryParam("nombreUsuario");
    Optional<String> email = request.queryParam("email");

    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(usuarioService
            .findAllByFilter(nombreUsuario.orElse(null), email.orElse(null)), UsuarioDto.class);
  }

  // GET BY ID
  public Mono<ServerResponse> getById(ServerRequest request) {
    String id = request.pathVariable("id");

    return usuarioService.findById(id)
        .flatMap(usuarioDto ->
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(usuarioDto))
        ).switchIfEmpty(response404);
  }

  // CREATE USUARIO
  public Mono<ServerResponse> create(ServerRequest request) {
    Mono<UsuarioDto> usuarioDtoMono = request.bodyToMono(UsuarioDto.class);

    return usuarioDtoMono
        .flatMap(usuarioDto -> usuarioService.create(usuarioDto)
            .flatMap(usuarioDtoSaved -> ServerResponse.created(URI.create("/v2/usuarios/".concat(usuarioDtoSaved.id())))
                .body(fromValue(usuarioDtoSaved))))
        .switchIfEmpty(response400);
  }

  // UPDATE USUARIO
  public Mono<ServerResponse> update(ServerRequest request) {
    Mono<UsuarioDto> usuarioDtoMono = request.bodyToMono(UsuarioDto.class);
    String id = request.pathVariable("id");

    return usuarioDtoMono
        .flatMap(usuarioDto -> usuarioService.update(id, usuarioDto)
            .flatMap(usuarioDtoUpdated -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(usuarioDtoUpdated))))
        .switchIfEmpty(response404);
  }

  // DELETE USUARIO
  public Mono<ServerResponse> delete(ServerRequest request) {
    String id = request.pathVariable("id");
    Mono<Void> usuarioDtoMono = usuarioService.delete(id);

    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(usuarioDtoMono, Void.class);
  }
}
