package pe.marcolopez.apps.licenciapp.service;

import pe.marcolopez.apps.licenciapp.dto.ClienteCreateDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteQueryDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteUpdateDto;
import pe.marcolopez.apps.licenciapp.dto.TramiteCreateDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {

  Flux<ClienteQueryDto> findAll();

  Mono<ClienteQueryDto> findById(String id);

  Mono<ClienteQueryDto> create(ClienteCreateDto clienteDto);

  Mono<ClienteQueryDto> update(String id, ClienteUpdateDto clienteUpdateDto);

  Mono<Void> delete(String id);

  Mono<ClienteQueryDto> addTramite(String clienteId, TramiteCreateDto tramiteCreateDto);
}
