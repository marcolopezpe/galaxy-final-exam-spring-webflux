package pe.marcolopez.apps.licenciapp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.licenciapp.dto.ClienteCreateDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteQueryDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteUpdateDto;
import pe.marcolopez.apps.licenciapp.dto.TramiteCreateDto;
import pe.marcolopez.apps.licenciapp.mapper.ClienteMapper;
import pe.marcolopez.apps.licenciapp.repository.ClienteRepository;
import pe.marcolopez.apps.licenciapp.service.ClienteService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

  private final ClienteRepository clienteRepository;
  private final ClienteMapper clienteMapper;

  @Override
  public Flux<ClienteQueryDto> findAll() {
    return clienteRepository
        .findAll()
        .map(clienteMapper::docToDto)
        .switchIfEmpty(Flux.empty());
  }

  @Override
  public Mono<ClienteQueryDto> findById(String id) {
    return clienteRepository
        .findById(id)
        .map(clienteMapper::docToDto)
        .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<ClienteQueryDto> create(ClienteCreateDto clienteCreateDto) {
    return clienteRepository
        .save(clienteMapper.dtoToDoc(clienteCreateDto))
        .map(clienteMapper::docToDto)
        .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<ClienteQueryDto> update(String id, ClienteUpdateDto clienteUpdateDto) {
    return clienteRepository.findById(id)
        .flatMap(clienteDocument ->
            clienteRepository
                .save(clienteMapper.dtoToDocUpdate(clienteUpdateDto, clienteDocument))
                .map(clienteMapper::docToDto));
  }

  @Override
  public Mono<Void> delete(String id) {
    return clienteRepository
        .deleteById(id);
  }

  @Override
  public Mono<ClienteQueryDto> addTramite(String clienteId, TramiteCreateDto tramiteCreateDto) {
    return clienteRepository.findById(clienteId)
        .flatMap(clienteDocument ->
          clienteRepository
              .save(clienteMapper.tramiteDtoToDoc(clienteDocument, tramiteCreateDto))
              .map(clienteMapper::docToDto));
  }
}
