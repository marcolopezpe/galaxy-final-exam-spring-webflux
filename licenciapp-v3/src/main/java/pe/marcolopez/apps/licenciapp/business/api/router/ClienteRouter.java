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
import pe.marcolopez.apps.licenciapp.api.handler.ClienteHandler;
import pe.marcolopez.apps.licenciapp.dto.ClienteCreateDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteQueryDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteUpdateDto;
import pe.marcolopez.apps.licenciapp.dto.LicenciaUpdateDto;
import pe.marcolopez.apps.licenciapp.dto.TramiteCreateDto;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ClienteRouter {

  @RouterOperations({
      @RouterOperation(
          path = "/v2/clientes",
          method = RequestMethod.GET,
          beanClass = ClienteHandler.class,
          beanMethod = "getAllClientes",
          operation = @Operation(operationId = "getAllClientes",
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = ClienteQueryDto.class))),
                  @ApiResponse(responseCode = "204",
                      description = "Sin contenido.")
              },
              description = "Obtiene todos los Clientes registrados.",
              summary = "Obtiene todos los Clientes registrados.")
      ),
      @RouterOperation(
          path = "/v2/clientes/{id}",
          method = RequestMethod.GET,
          beanClass = ClienteHandler.class,
          beanMethod = "getById",
          operation = @Operation(operationId = "getById",
              parameters = {
                  @Parameter(in = ParameterIn.PATH,
                      name = "id",
                      description = "ID del Cliente.",
                      example = "66add3abdd80823a72b4a780")
              },
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = ClienteQueryDto.class)))
              },
              description = "Obtiene el Cliente registrado por su ID.",
              summary = "Obtiene el Cliente registrado por su ID.")
      ),
      @RouterOperation(
          path = "/v2/clientes",
          method = RequestMethod.POST,
          beanClass = ClienteHandler.class,
          beanMethod = "create",
          operation = @Operation(operationId = "create",
              responses = {
                  @ApiResponse(responseCode = "201",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = ClienteQueryDto.class))),
                  @ApiResponse(responseCode = "400",
                      description = "Cliente no valido.")
              },
              requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = ClienteCreateDto.class),
                  examples = @ExampleObject(value = """
                        {
                          "apellidos": "SMITH",
                          "nombres": "STEVE",
                          "numeroDocumento": "46981237",
                          "email": "SMITH_STEVE@OUTLOOK.COM",
                          "puntosAcumulados": "0",
                          "recordNumero": "3422323242024"
                        }
                      """))),
              description = "Crea un Cliente.",
              summary = "Crea un Cliente.")
      ),
      @RouterOperation(
          path = "/v2/clientes/{id}",
          method = RequestMethod.PUT,
          beanClass = ClienteHandler.class,
          beanMethod = "update",
          operation = @Operation(operationId = "update",
              parameters = {
                  @Parameter(in = ParameterIn.PATH,
                      name = "id",
                      description = "ID del Cliente.",
                      example = "66add3abdd80823a72b4a780")
              },
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = ClienteQueryDto.class))),
                  @ApiResponse(responseCode = "400",
                      description = "Cliente no valido.")
              },
              requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = ClienteUpdateDto.class),
                  examples = @ExampleObject(value = """
                        {
                        	"apellidos": "LOPEZ CAMACHO",
                        	"nombres": "MARCO",
                        	"numeroDocumento": "46981237",
                        	"email": "MARCOLOPEZPE@OUTLOOK.COM",
                        	"puntosAcumulados": "0",
                        	"recordNumero": "3422323242024"
                        }
                      """))),
              description = "Actualiza un Cliente.",
              summary = "Actualiza un Cliente.")
      ),
      @RouterOperation(
          path = "/v2/clientes/{id}",
          method = RequestMethod.DELETE,
          beanClass = ClienteHandler.class,
          beanMethod = "delete",
          operation = @Operation(operationId = "delete",
              parameters = {
                  @Parameter(in = ParameterIn.PATH,
                      name = "id",
                      description = "ID del Cliente.",
                      example = "66add3abdd80823a72b4a780")
              },
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.")
              },
              description = "Elimina un Cliente.",
              summary = "Elimina un Cliente.")
      ),
      @RouterOperation(
          path = "/v2/clientes/{id}/add-tramite",
          method = RequestMethod.PATCH,
          beanClass = ClienteHandler.class,
          beanMethod = "addTramiteToCliente",
          operation = @Operation(operationId = "addTramiteToCliente",
              parameters = {
                  @Parameter(in = ParameterIn.PATH,
                      name = "id",
                      description = "ID del Cliente.",
                      example = "66add3abdd80823a72b4a780")
              },
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = ClienteQueryDto.class))),
                  @ApiResponse(responseCode = "400",
                      description = "Cliente no valido.")
              },
              requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = TramiteCreateDto.class),
                  examples = @ExampleObject(value = """
                        {
                        	"numeroLicencia": "Q46981237",
                        	"claseCategoria": "A IIa",
                        	"tramite": "Revalidación",
                        	"fechaExpedicion": "19/09/2025",
                        	"fechaEmision": "08/08/2025",
                        	"fechaRevalidacion": "08/01/2035",
                        	"tipoLicencia": "FISICA",
                        	"restricciones": "LENTES",
                        	"centroEmision": "MTC - LINCE"
                        }
                      """))),
              description = "Agrega un Tramite y actualiza datos de Licencia de un Cliente.",
              summary = "Agrega un Tramite y actualiza datos de Licencia de un Cliente.")
      ),
      @RouterOperation(
          path = "/v2/clientes/{id}/update-licencia",
          method = RequestMethod.PATCH,
          beanClass = ClienteHandler.class,
          beanMethod = "updateLicenciaToCliente",
          operation = @Operation(operationId = "updateLicenciaToCliente",
              parameters = {
                  @Parameter(in = ParameterIn.PATH,
                      name = "id",
                      description = "ID del Cliente.",
                      example = "66add3abdd80823a72b4a780")
              },
              responses = {
                  @ApiResponse(responseCode = "200",
                      description = "Operacion exitosa.",
                      content = @Content(schema = @Schema(implementation = ClienteQueryDto.class))),
                  @ApiResponse(responseCode = "400",
                      description = "Cliente no valido.")
              },
              requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = LicenciaUpdateDto.class),
                  examples = @ExampleObject(value = """
                        {
                        	"puntosAcumulados": "100",
                        	"licencia": {
                        		"tipoLicencia": "DIGITAL WEB",
                        		"restricciones": "CON LENTES OSCUROS"
                        	}
                        }
                      """))),
              description = "Actualiza datos de Cliente y su Licencia.",
              summary = "Actualiza datos de Cliente y su Licencia.")
      ),
  })
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
            clienteHandler::addTramiteToCliente)
        .andRoute(PATCH("/v2/clientes/{id}/update-licencia"),
            clienteHandler::updateLicenciaToCliente);
  }
}
