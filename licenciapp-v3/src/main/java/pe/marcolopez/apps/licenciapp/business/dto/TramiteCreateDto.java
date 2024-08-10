package pe.marcolopez.apps.licenciapp.business.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record TramiteCreateDto(
    @Size(min = 5, max = 20, message = "El Numero de Licencia debe ser entre 5 y 20 caracteres")
    String numeroLicencia,
    @Pattern(regexp = "A-I|A-IIa|A-IIb|A-IIIa|A-IIIb|A-IIIc|B-I|B-IIa|B-IIb|B-IIc",
        message = "La Clase Categoria debe ser uno de ellos: A-I, A-IIa, A-IIb, A-IIIa, A-IIIb, A-IIIc, B-I, B-IIa, B-IIb, B-IIc")
    String claseCategoria,
    @Size(min = 5, max = 100, message = "El Tramite debe ser entre 5 y 20 caracteres")
    String tramite,
    @Pattern(regexp = "^$|^([0-9]{2}/[0-9]{2}/[0-9]{4})$", message = "Formato de Fecha Expedicion debe ser dd/mm/yyyy")
    String fechaExpedicion,
    @Pattern(regexp = "^$|^([0-9]{2}/[0-9]{2}/[0-9]{4})$", message = "Formato de Fecha Expedicion debe ser dd/mm/yyyy")
    String fechaEmision,
    @Pattern(regexp = "^$|^([0-9]{2}/[0-9]{2}/[0-9]{4})$", message = "Formato de Fecha Expedicion debe ser dd/mm/yyyy")
    String fechaRevalidacion,
    @Pattern(regexp = "FISICO|DIGITAL", message = "El Tipo de Licencia debe ser 'FISICO' o 'DIGITAL'")
    String tipoLicencia,
    @Size(max = 250, message = "Las Restricciones son maximo 250 caracteres")
    String restricciones,
    @Size(min = 5, max = 250, message = "El Centro de Emision debe ser entre 5 y 250 caracteres")
    String centroEmision) {
}
