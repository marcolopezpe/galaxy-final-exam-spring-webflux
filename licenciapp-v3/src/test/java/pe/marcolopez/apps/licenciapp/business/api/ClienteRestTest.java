package pe.marcolopez.apps.licenciapp.business.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import pe.marcolopez.apps.licenciapp.business.dto.ClienteQueryDto;
import pe.marcolopez.apps.licenciapp.security.auth.JwtTokenProvider;
import pe.marcolopez.apps.licenciapp.security.dto.UsuarioDto;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static pe.marcolopez.apps.licenciapp.business.util.PayloadUtil.*;

@Slf4j
@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteRestTest {

  static String TOKEN;

  @Autowired
  private WebTestClient webTestClient;
  @Autowired
  private JwtTokenProvider jwtTokenProvider;
  private ClienteQueryDto clienteQueryDto;

  @BeforeEach
  void setUp() {
    TOKEN = this.jwtTokenProvider.generateToken(
        UsuarioDto.builder()
            .id("idf989324")
            .nombreUsuario("admin")
            .roles(new String[]{"admin"})
            .build()
    );
  }

  @Test
  @Order(0)
  @DisplayName("Retorna 400 cuando el cliente fue invalido.")
  void return400WhenClienteWasCreatedSuccessfully() {
    webTestClient
        .post()
        .uri("/v2/clientes")
        .headers(http -> http.setBearerAuth(TOKEN))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(
            dtoToCreateInvalid()
        ))
        .exchange()
        .expectStatus().isBadRequest()
        .expectBody()
        .consumeWith(response -> {
          log.error("### Error 400: {}", response);
        });
  }

  @Test
  @Order(1)
  @DisplayName("Retorna 201 cuando el cliente fue creado existosamente.")
  void return201WhenClienteWasCreatedSuccessfully() throws URISyntaxException {
    Flux<ClienteQueryDto> clienteFlux = webTestClient
        .post()
        .uri("/v2/clientes")
        .headers(http -> http.setBearerAuth(TOKEN))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(
            dtoToCreate()
        ))
        .exchange()
        .expectStatus().isCreated()
        .returnResult(ClienteQueryDto.class)
        .getResponseBody()
        .log();

    clienteFlux.next().subscribe(cliente -> this.clienteQueryDto = cliente);

    assertNotNull(this.clienteQueryDto);
  }

  @Test
  @Order(2)
  @DisplayName("Retorna 200 cuando todos los clientes fueron obtenidos exitosamente.")
  public void return200WhenGetAllClientesWasSuccessfully() {
    webTestClient
        .get()
        .uri("/v2/clientes")
        .headers(http -> http.setBearerAuth(TOKEN))
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .returnResult(ClienteQueryDto.class);
  }

  @Test
  @Order(3)
  @DisplayName("Retorna 200 cuando el cliente fue actualizado existosamente.")
  void return200WhenClienteWasUpdatedSuccessfully() {
    Flux<ClienteQueryDto> clienteFlux = webTestClient
        .put()
        .uri("/v2/clientes/{id}", clienteQueryDto.id())
        .headers(http -> http.setBearerAuth(TOKEN))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(
            dtoToUpdate()
        ))
        .exchange()
        .expectStatus().isOk()
        .returnResult(ClienteQueryDto.class)
        .getResponseBody()
        .log();

    StepVerifier.create(clienteFlux)
        .expectSubscription()
        .expectNextMatches(cliente -> cliente.email().equalsIgnoreCase("ELCORREOACTUALIZADO@OUTLOOK.COM"))
        .verifyComplete();
  }

  @Test
  @Order(4)
  @DisplayName("Retorna 200 cuando el cliente fue eliminado existosamente.")
  void return200WhenClienteWasDeletedSuccessfully() {
    Flux<Void> voidFlux = webTestClient
        .delete()
        .uri("/v2/clientes/{id}", clienteQueryDto.id())
        .headers(http -> http.setBearerAuth(TOKEN))
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .returnResult(Void.class).getResponseBody();

    StepVerifier.create(voidFlux)
        .expectSubscription()
        .verifyComplete();
  }
}
