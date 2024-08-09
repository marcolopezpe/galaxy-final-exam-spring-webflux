package pe.marcolopez.apps.licenciapp.business.service;

import pe.marcolopez.apps.licenciapp.security.dto.UsuarioDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioService {

  Flux<UsuarioDto> findAll();

  Flux<UsuarioDto> findAllByFilter(String nombreUsuario, String email);

  Mono<UsuarioDto> findById(String id);

  Mono<UsuarioDto> create(UsuarioDto usuarioDto);

  Mono<UsuarioDto> update(String id, UsuarioDto usuarioDto);

  Mono<Void> delete(String id);
}
