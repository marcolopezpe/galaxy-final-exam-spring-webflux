package pe.marcolopez.apps.licenciapp.business.dto;

import lombok.Builder;

@Builder
public record TramiteQueryDto(String numeroLicencia,
                              String claseCategoria,
                              String tramite,
                              String fechaExpedicion,
                              String fechaEmision,
                              String fechaRevalidacion) {
}
