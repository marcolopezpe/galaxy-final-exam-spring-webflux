package pe.marcolopez.apps.licenciapp.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.apps.licenciapp.dto.ClienteCreateDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteQueryDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteUpdateDto;
import pe.marcolopez.apps.licenciapp.dto.TramiteCreateDto;
import pe.marcolopez.apps.licenciapp.service.ClienteService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/clientes")
public class ClienteRest {

  private final ClienteService clienteService;

  @GetMapping
  public Flux<ClienteQueryDto> getAllClientes() {
    return clienteService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<ClienteQueryDto> getClienteById(@PathVariable String id) {
    return clienteService.findById(id);
  }

  @PostMapping
  public Mono<ClienteQueryDto> createCliente(@RequestBody ClienteCreateDto clienteCreateDto) {
    return clienteService.create(clienteCreateDto);
  }

  @PutMapping("/{id}")
  public Mono<ClienteQueryDto> update(@PathVariable String id,
                                      @RequestBody ClienteUpdateDto clienteUpdateDto) {
    return clienteService.update(id, clienteUpdateDto);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable String id) {
    return clienteService.delete(id);
  }

  @PatchMapping("/{clienteId}/add-tramite")
  public Mono<ClienteQueryDto> addTramiteToCliente(@PathVariable String clienteId,
                                                   @RequestBody TramiteCreateDto tramiteCreateDto) {
    return clienteService.addTramite(clienteId, tramiteCreateDto);
  }
}
