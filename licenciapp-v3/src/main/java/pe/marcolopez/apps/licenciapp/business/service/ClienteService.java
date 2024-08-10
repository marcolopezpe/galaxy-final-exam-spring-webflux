package pe.marcolopez.apps.licenciapp.business.service;

import pe.marcolopez.apps.licenciapp.business.dto.ClienteQueryDto;
import pe.marcolopez.apps.licenciapp.business.dto.ClienteCreateDto;
import pe.marcolopez.apps.licenciapp.business.dto.ClienteUpdateDto;
import pe.marcolopez.apps.licenciapp.business.dto.ClienteUpdateLicenciaDto;
import pe.marcolopez.apps.licenciapp.business.dto.TramiteCreateDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {

  Flux<ClienteQueryDto> findAll();

  Mono<ClienteQueryDto> findById(String id);

  Mono<ClienteQueryDto> create(ClienteCreateDto clienteDto);

  Mono<ClienteQueryDto> update(String id, ClienteUpdateDto clienteUpdateDto);

  Mono<Void> delete(String id);

  Mono<ClienteQueryDto> addTramite(String clienteId, TramiteCreateDto tramiteCreateDto);

  Mono<ClienteQueryDto> updateLicencia(String clienteId, ClienteUpdateLicenciaDto clienteUpdateLicenciaDto);
}
