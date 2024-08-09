package pe.marcolopez.apps.licenciapp.business.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.licenciapp.business.repository.UsuarioRepository;
import pe.marcolopez.apps.licenciapp.business.service.UsuarioService;
import pe.marcolopez.apps.licenciapp.security.dto.UsuarioDto;
import pe.marcolopez.apps.licenciapp.business.mapper.UsuarioMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final UsuarioMapper usuarioMapper;

  @Override
  public Flux<UsuarioDto> findAll() {
    return usuarioRepository
        .findAll()
        .map(usuarioMapper::docToDto)
        .switchIfEmpty(Flux.empty());
  }

  @Override
  public Flux<UsuarioDto> findAllByFilter(String nombreUsuario, String email) {
    return usuarioRepository
        .findAllByFilter(nombreUsuario, email)
        .map(usuarioMapper::docToDto)
        .switchIfEmpty(Flux.empty());
  }

  @Override
  public Mono<UsuarioDto> findById(String id) {
    return usuarioRepository
        .findById(id)
        .map(usuarioMapper::docToDto)
        .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<UsuarioDto> create(UsuarioDto usuarioDto) {
    return usuarioRepository
        .save(usuarioMapper.dtoToDoc(usuarioDto))
        .map(usuarioMapper::docToDto)
        .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<UsuarioDto> update(String id, UsuarioDto usuarioDto) {
    return usuarioRepository.findById(id)
        .flatMap(usuarioDocument ->
            usuarioRepository
                .save(usuarioMapper.dtoToDocUpdate(usuarioDto, usuarioDocument))
                .map(usuarioMapper::docToDto));
  }

  @Override
  public Mono<Void> delete(String id) {
    return usuarioRepository
        .deleteById(id);
  }
}
