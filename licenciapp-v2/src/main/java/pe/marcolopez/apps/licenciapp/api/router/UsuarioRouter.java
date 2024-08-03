package pe.marcolopez.apps.licenciapp.api.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.marcolopez.apps.licenciapp.api.handler.UsuarioHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UsuarioRouter {

  @Bean
  public RouterFunction<ServerResponse> routeUsuario(UsuarioHandler usuarioHandler) {
    return RouterFunctions
        .route(GET("/v2/usuarios"),
            usuarioHandler::getAllUsuarios)
        .andRoute(GET("/v2/usuarios/filter"),
            usuarioHandler::getAllByFilter)
        .andRoute(GET("/v2/usuarios/{id}"),
            usuarioHandler::getById)
        .andRoute(POST("/v2/usuarios"),
            usuarioHandler::create)
        .andRoute(PUT("/v2/usuarios/{id}"),
            usuarioHandler::update)
        .andRoute(DELETE("/v2/usuarios/{id}"),
            usuarioHandler::delete);
  }
}
