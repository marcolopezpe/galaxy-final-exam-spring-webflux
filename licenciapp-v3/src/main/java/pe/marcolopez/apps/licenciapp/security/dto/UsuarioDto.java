package pe.marcolopez.apps.licenciapp.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import pe.marcolopez.apps.licenciapp.commons.dto.constraint.EmailValid;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
                         String email,
                         @Size(min = 8, max = 20, message = "La  Contrasena debe ser entre 8 y 20 caracteres")
                         String contrasena,
                         String[] roles,
                         @Pattern(regexp = "ACTIVO|BLOQUEADO", message = "El valor debe ser ACTIVO o BLOQUEADO")
                         String estado) {
}
