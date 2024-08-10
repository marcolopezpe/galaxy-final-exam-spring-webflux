package pe.marcolopez.apps.licenciapp.security.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.licenciapp.business.exceptions.web.NotFoundException;
import pe.marcolopez.apps.licenciapp.security.auth.JwtTokenProvider;
import pe.marcolopez.apps.licenciapp.security.dto.LoginRequestDto;
import pe.marcolopez.apps.licenciapp.security.dto.LoginResponseDto;
import pe.marcolopez.apps.licenciapp.security.repository.UsuarioRepository;
import pe.marcolopez.apps.licenciapp.security.service.UsuarioService;
import pe.marcolopez.apps.licenciapp.security.dto.UsuarioDto;
import pe.marcolopez.apps.licenciapp.security.mapper.UsuarioMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final UsuarioMapper usuarioMapper;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

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

  @Override
  public Mono<LoginResponseDto> login(LoginRequestDto loginDto) {
    return usuarioRepository
        .findTop1ByNombreUsuarioIgnoreCase(loginDto.usuario())
        .filter(usuarioDocument ->
            passwordEncoder.matches(loginDto.contrasena(), usuarioDocument.getContrasena())
                && usuarioDocument.getEstado().equalsIgnoreCase("ACTIVO"))
        .map(usuarioDocument ->
            new LoginResponseDto(jwtTokenProvider.generateToken(usuarioMapper.docToDto(usuarioDocument)))
        )
        .switchIfEmpty(Mono.error(new NotFoundException("El Usuario y/o contrasena incorrecta.")));
  }
}
