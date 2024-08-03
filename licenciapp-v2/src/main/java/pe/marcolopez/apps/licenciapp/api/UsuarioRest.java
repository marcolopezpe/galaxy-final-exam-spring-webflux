package pe.marcolopez.apps.licenciapp.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.apps.licenciapp.dto.UsuarioDto;
import pe.marcolopez.apps.licenciapp.service.UsuarioService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioRest {

  private final UsuarioService usuarioService;

  @GetMapping
  public Flux<UsuarioDto> getAllUsuarios() {
    return usuarioService.findAll();
  }

  @GetMapping("/filter")
  public Flux<UsuarioDto> getAllUsuariosByFilter(
      @RequestParam(value = "nombreUsuario", required = false) String nombreUsuario,
      @RequestParam(value = "email", required = false) String email) {
    return usuarioService.findAllByFilter(nombreUsuario, email);
  }

  @GetMapping("/{id}")
  public Mono<UsuarioDto> getUsuario(@PathVariable String id) {
    return usuarioService.findById(id);
  }

  @PostMapping
  public Mono<UsuarioDto> create(@RequestBody UsuarioDto usuarioDto) {
    return usuarioService.create(usuarioDto);
  }

  @PutMapping("/{id}")
  public Mono<UsuarioDto> update(@PathVariable String id,
                                 @RequestBody UsuarioDto usuarioDto) {
    return usuarioService.update(id, usuarioDto);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable String id) {
    return usuarioService.delete(id);
  }
}
