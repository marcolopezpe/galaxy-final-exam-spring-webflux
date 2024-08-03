package pe.marcolopez.apps.licenciapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteQueryDto(String id,
                              String apellidos,
                              String nombres,
                              String numeroDocumento,
                              String email,
                              LicenciaQueryDto licencia,
                              String puntosAcumulados,
                              String recordNumero,
                              List<TramiteQueryDto> tramites) {
}
