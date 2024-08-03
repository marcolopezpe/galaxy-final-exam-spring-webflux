package pe.marcolopez.apps.licenciapp.dto;

import lombok.Builder;

@Builder
public record ClienteCreateDto(String apellidos,
                               String nombres,
                               String numeroDocumento,
                               String email,
                               String puntosAcumulados,
                               String recordNumero) {
}
