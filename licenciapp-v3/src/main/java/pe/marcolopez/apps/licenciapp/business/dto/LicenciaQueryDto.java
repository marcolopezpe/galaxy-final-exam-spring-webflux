package pe.marcolopez.apps.licenciapp.dto;

import lombok.Builder;

@Builder
public record LicenciaQueryDto(String claseCategoria,
                               String numeroLicencia,
                               String tipoLicencia,
                               String fechaExpedicion,
                               String restricciones,
                               String vigenteHasta,
                               String centroEmision) {
}
