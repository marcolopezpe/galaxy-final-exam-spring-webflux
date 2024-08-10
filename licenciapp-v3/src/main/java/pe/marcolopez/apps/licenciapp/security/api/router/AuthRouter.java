package pe.marcolopez.apps.licenciapp.security.api.router;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.marcolopez.apps.licenciapp.security.api.handler.AuthHandler;
import pe.marcolopez.apps.licenciapp.security.dto.LoginResponseDto;

@Configuration
public class AuthRouter {

  @RouterOperations({
      @RouterOperation(
          path = "/v2/auth/login",
          method = RequestMethod.POST,
          beanClass = AuthHandler.class,
          beanMethod = "login",
          operation = @Operation(operationId = "login",
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
                  @ApiResponse(responseCode = "400",
                      description = "Error al enviar usuario y contrasena.")
              },
              description = "Permite obtener el Token de autorizacion",
              summary = "Permite obtener el Token de autorizacion"
          )
      )
  })
  @Bean
  public RouterFunction<ServerResponse> authRoute(AuthHandler authHandler) {
    return RouterFunctions
        .route()
        .POST("/v2/auth/login", authHandler::login)
        .build();
  }
}
