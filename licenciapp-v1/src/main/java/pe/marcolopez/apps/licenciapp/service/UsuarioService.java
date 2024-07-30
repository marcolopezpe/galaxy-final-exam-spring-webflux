package pe.marcolopez.apps.licenciapp.service;

import pe.marcolopez.apps.licenciapp.dto.UsuarioDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioService {

  Flux<UsuarioDto> findAll();

  Mono<UsuarioDto> findById(String id);

  Mono<UsuarioDto> save(UsuarioDto usuarioDto);

  Mono<UsuarioDto> update(String id, UsuarioDto usuarioDto);

  Mono<Void> delete(String id);
}
