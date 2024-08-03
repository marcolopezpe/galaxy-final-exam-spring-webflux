package pe.marcolopez.apps.licenciapp.dto;

import lombok.Builder;

@Builder
public record LicenciaUpdateDto(String tipoLicencia,
                                String restricciones) {
}
