package pe.marcolopez.apps.licenciapp.api.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.marcolopez.apps.licenciapp.api.handler.ClienteHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ClienteRouter {

  @Bean
  public RouterFunction<ServerResponse> routeCliente(ClienteHandler clienteHandler) {
    return RouterFunctions
        .route(GET("/v2/clientes"),
            clienteHandler::getAllClientes)
        .andRoute(GET("/v2/clientes/{id}"),
            clienteHandler::getById)
        .andRoute(POST("/v2/clientes"),
            clienteHandler::create)
        .andRoute(PUT("/v2/clientes/{id}"),
            clienteHandler::update)
        .andRoute(DELETE("/v2/clientes/{id}"),
            clienteHandler::delete)
        .andRoute(PATCH("/v2/clientes/{id}/add-tramite"),
            clienteHandler::addTramiteToCliente);
  }
}
