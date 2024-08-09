package pe.marcolopez.apps.licenciapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import pe.marcolopez.apps.licenciapp.dto.constraint.EmailValid;

@Builder
public record ClienteUpdateDto(@Size(min = 2, max = 250, message = "Los Apellidos deben ser entre 2 y 250 caracteres")
                               String apellidos,
                               @Size(min = 2, max = 250, message = "Los Nombres deben ser entre 2 y 250 caracteres")
                               String nombres,
                               @Size(min = 6, max = 30, message = "El Numero de Documento debe ser entre 6 y 30 caracteres")
                               String numeroDocumento,
                               @EmailValid(message = "Formato de Email incorrecto")
                               String email,
                               @Min(value = 0, message = "El valor minimo es 0")
                               @Pattern(regexp= "\\d{6}", message = "Los Puntos Acumulados deben ser numericos")
                               String puntosAcumulados,
                               @NotEmpty(message = "Campo obligatorio")
                               @Pattern(regexp= "\\d{6}", message = "El Record Numero solo es numerico")
                               String recordNumero) {
}
