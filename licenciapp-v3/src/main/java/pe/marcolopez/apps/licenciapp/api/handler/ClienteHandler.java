package pe.marcolopez.apps.licenciapp.api.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.marcolopez.apps.licenciapp.dto.ClienteCreateDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteQueryDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteUpdateDto;
import pe.marcolopez.apps.licenciapp.dto.TramiteCreateDto;
import pe.marcolopez.apps.licenciapp.service.ClienteService;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class ClienteHandler {

  private final Mono<ServerResponse> response404 = ServerResponse.notFound().build();
  private final Mono<ServerResponse> response400 = ServerResponse.badRequest().build();
  private final ClienteService clienteService;

  // GET ALL CLIENTES
  public Mono<ServerResponse> getAllClientes(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(clienteService.findAll(), ClienteQueryDto.class);
  }

  // GET BY ID
  public Mono<ServerResponse> getById(ServerRequest request) {
    String id = request.pathVariable("id");

    return clienteService.findById(id)
        .flatMap(clienteQueryDto ->
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(clienteQueryDto))
        ).switchIfEmpty(response404);
  }

  // CREATE CLIENTE
  public Mono<ServerResponse> create(ServerRequest request) {
    Mono<ClienteCreateDto> clienteQueryDtoMono = request.bodyToMono(ClienteCreateDto.class);

    return clienteQueryDtoMono
        .flatMap(clienteCreateDto -> clienteService.create(clienteCreateDto)
            .flatMap(clienteQueryDto -> ServerResponse.created(URI.create("/v2/clientes/".concat(clienteQueryDto.id())))
                .body(fromValue(clienteQueryDto))))
        .switchIfEmpty(response400);
  }

  // UPDATE CLIENTE
  public Mono<ServerResponse> update(ServerRequest request) {
    Mono<ClienteUpdateDto> usuarioDtoMono = request.bodyToMono(ClienteUpdateDto.class);
    String id = request.pathVariable("id");

    return usuarioDtoMono
        .flatMap(clienteUpdateDto -> clienteService.update(id, clienteUpdateDto)
            .flatMap(clienteQueryDto -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(clienteQueryDto))))
        .switchIfEmpty(response404);
  }

  // DELETE CLIENTE
  public Mono<ServerResponse> delete(ServerRequest request) {
    String id = request.pathVariable("id");
    Mono<Void> clienteDtoMono = clienteService.delete(id);

    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(clienteDtoMono, Void.class);
  }

  // ADD TRAMITE
  public Mono<ServerResponse> addTramiteToCliente(ServerRequest request) {
    Mono<TramiteCreateDto> tramiteCreateDtoMono = request.bodyToMono(TramiteCreateDto.class);
    String id = request.pathVariable("id");

    return tramiteCreateDtoMono
        .flatMap(tramiteCreateDto -> clienteService.addTramite(id, tramiteCreateDto)
            .flatMap(clienteQueryDto -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(clienteQueryDto))))
        .switchIfEmpty(response404);
  }
}
