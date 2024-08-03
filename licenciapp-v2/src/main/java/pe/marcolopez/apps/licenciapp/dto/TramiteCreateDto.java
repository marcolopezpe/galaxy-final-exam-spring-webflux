package pe.marcolopez.apps.licenciapp.dto;

import lombok.Builder;

@Builder
public record TramiteCreateDto(String numeroLicencia,
                               String claseCategoria,
                               String tramite,
                               String fechaExpedicion,
                               String fechaEmision,
                               String fechaRevalidacion,
                               String tipoLicencia,
                               String restricciones,
                               String centroEmision) {
}
