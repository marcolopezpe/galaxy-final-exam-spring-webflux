package pe.marcolopez.apps.licenciapp.business.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LicenciaUpdateDto(
    @Pattern(regexp = "FISICO|DIGITAL", message = "El Tipo de Licencia debe ser 'FISICO' o 'DIGITAL'")
    String tipoLicencia,
    @Size(max = 250, message = "Las Restricciones son maximo 250 caracteres")
    String restricciones) {
}
