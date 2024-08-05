package pe.marcolopez.apps.licenciapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record ClienteUpdateLicenciaDto(@Min(value = 0, message = "El valor minimo es 0")
                                       @Pattern(regexp = "\\d{6}", message = "Los Puntos Acumulados deben ser numericos")
                                       String puntosAcumulados,
                                       LicenciaUpdateDto licencia) {
}
