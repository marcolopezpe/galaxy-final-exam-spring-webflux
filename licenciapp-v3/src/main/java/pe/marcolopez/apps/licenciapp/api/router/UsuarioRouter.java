package pe.marcolopez.apps.licenciapp.api.router;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.marcolopez.apps.licenciapp.api.handler.UsuarioHandler;
import pe.marcolopez.apps.licenciapp.dto.UsuarioDto;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UsuarioRouter {

  @RouterOperations({
      @RouterOperation(
          path = "/v2/usuarios",
          method = RequestMethod.GET,
          beanClass = UsuarioHandler.class,
          beanMethod = "getAllUsuarios",
          operation = @Operation(operationId = "getAllUsuarios",
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = UsuarioDto.class))),
                  @ApiResponse(responseCode = "204",
                      description = "Sin contenido.")
              },
              description = "Obtiene todos los Usuarios registrados.",
              summary = "Obtiene todos los Usuarios registrados.")
      ),
      @RouterOperation(
          path = "/v2/usuarios/filter",
          method = RequestMethod.GET,
          beanClass = UsuarioHandler.class,
          beanMethod = "getAllByFilter",
          operation = @Operation(operationId = "getAllByFilter",
              parameters = {
                  @Parameter(in = ParameterIn.QUERY,
                      name = "nombreUsuario",
                      description = "Nombre del Usuario a buscar.",
                      example = "mlopez"),
                  @Parameter(in = ParameterIn.QUERY,
                      name = "email",
                      description = "Email del Usuario a buscar.",
                      example = "mlopez@gmail.com")
              },
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = UsuarioDto.class))),
                  @ApiResponse(responseCode = "204",
                      description = "Sin contenido.")
              },
              description = "Obtiene todos los Usuarios registrados en base a un filtro.",
              summary = "Obtiene todos los Usuarios registrados en base a un filtro.")
      ),
      @RouterOperation(
          path = "/v2/usuarios/{id}",
          method = RequestMethod.GET,
          beanClass = UsuarioHandler.class,
          beanMethod = "getById",
          operation = @Operation(operationId = "getById",
              parameters = {
                  @Parameter(in = ParameterIn.PATH,
                      name = "id",
                      description = "ID del Usuario.",
                      example = "66add3abdd80823a72b4a780")
              },
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = UsuarioDto.class)))
              },
              description = "Obtiene el Usuario registrado por su ID.",
              summary = "Obtiene el Usuario registrado por su ID.")
      ),
      @RouterOperation(
          path = "/v2/usuarios",
          method = RequestMethod.POST,
          beanClass = UsuarioHandler.class,
          beanMethod = "create",
          operation = @Operation(operationId = "create",
              responses = {
                  @ApiResponse(responseCode = "201",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = UsuarioDto.class))),
                  @ApiResponse(responseCode = "400",
                      description = "Usuario no valido.")
              },
              requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = UsuarioDto.class),
                  examples = @ExampleObject(value = """
                        {
                        	"apellidos": "MEZA",
                        	"nombres": "OSSCAR",
                        	"nombreUsuario": "OMEZA",
                        	"email": "OMEZA2@OUTLOOK.COM"
                        }
                      """))),
              description = "Crea un Usuario.",
              summary = "Crea un Usuario.")
      ),
      @RouterOperation(
          path = "/v2/usuarios/{id}",
          method = RequestMethod.PUT,
          beanClass = UsuarioHandler.class,
          beanMethod = "update",
          operation = @Operation(operationId = "update",
              parameters = {
                  @Parameter(in = ParameterIn.PATH,
                      name = "id",
                      description = "ID del Usuario.",
                      example = "66add3abdd80823a72b4a780")
              },
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = UsuarioDto.class))),
                  @ApiResponse(responseCode = "400",
                      description = "Usuario no valido.")
              },
              requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = UsuarioDto.class),
                  examples = @ExampleObject(value = """
                        {
                        	"apellidos": "MEZA QUISPE",
                        	"nombres": "OSCAR ANTONIO",
                        	"nombreUsuario": "OAMEZA",
                        	"email": "OMEZA@gmail.COM"
                        }
                      """))),
              description = "Actualiza un Usuario.",
              summary = "Actualiza un Usuario.")
      ),
      @RouterOperation(
          path = "/v2/usuarios/{id}",
          method = RequestMethod.DELETE,
          beanClass = UsuarioHandler.class,
          beanMethod = "delete",
          operation = @Operation(operationId = "delete",
              parameters = {
                  @Parameter(in = ParameterIn.PATH,
                      name = "id",
                      description = "ID del Usuario.",
                      example = "66add3abdd80823a72b4a780")
              },
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.")
              },
              description = "Elimina un Usuario.",
              summary = "Elimina un Usuario.")
      ),
  })
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
