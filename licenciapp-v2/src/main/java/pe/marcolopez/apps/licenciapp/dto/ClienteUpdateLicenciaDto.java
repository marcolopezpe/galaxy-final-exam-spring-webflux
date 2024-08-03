package pe.marcolopez.apps.licenciapp.dto;

import lombok.Builder;

@Builder
public record ClienteUpdateLicenciaDto(String puntosAcumulados,
                                       LicenciaUpdateDto licencia) {
}
