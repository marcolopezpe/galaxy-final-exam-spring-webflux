package pe.marcolopez.apps.licenciapp.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.apps.licenciapp.dto.UsuarioDto;
import pe.marcolopez.apps.licenciapp.service.UsuarioService;
import reactor.core.publisher.Flux;

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
}
