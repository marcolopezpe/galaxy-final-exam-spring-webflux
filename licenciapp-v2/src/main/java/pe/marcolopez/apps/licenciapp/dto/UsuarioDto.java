package pe.marcolopez.apps.licenciapp.dto;

import lombok.Builder;

@Builder
public record UsuarioDto(String id,
                         String apellidos,
                         String nombres,
                         String nombreUsuario,
                         String email) {
}
