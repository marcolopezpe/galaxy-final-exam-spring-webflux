package pe.marcolopez.apps.licenciapp.security.dto;

import lombok.Builder;

@Builder
public record UsuarioSubjectDto(String id,
                                String usuario) {
}
