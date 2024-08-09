package pe.marcolopez.apps.licenciapp.business.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import pe.marcolopez.apps.licenciapp.business.dto.constraint.EmailValid;

@Builder
public record UsuarioDto(String id,
                         @Size(min = 2, max = 250, message = "Los Apellidos deben ser entre 2 y 250 caracteres")
                         String apellidos,
                         @Size(min = 2, max = 250, message = "Los Nombres deben ser entre 2 y 250 caracteres")
                         String nombres,
                         @Size(min = 2, max = 250, message = "Los Nombres deben ser entre 2 y 250 caracteres")
                         @Pattern(regexp = "^\\p{Alnum}{1,32}$")
                         String nombreUsuario,
                         @EmailValid(message = "Formato de Email incorrecto")
                         String email) {
}
