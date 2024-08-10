package pe.marcolopez.apps.licenciapp.security.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record LoginRequestDto(@NotEmpty(message = "El Usuario es obligatorio.")
                              String usuario,
                              @NotEmpty(message = "La Contrasena es obligatoria.")
                              String contrasena) {
}
