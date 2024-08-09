package pe.marcolopez.apps.licenciapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "LicenciApp - Aplicacion Web para Licencias de Conducir",
		version = "1.0.0",
		description = "Documentacion de API v1.0.0",
		contact = @Contact(name = "Marco Lopez", email = "marcolopezpe@outlook.com", url = "https://marcolopez.pe"),
		license = @License(name = "Terminos y condiciones", url = "https://marcolopez.pe/license")
))
@SpringBootApplication
public class LicenciAppApplication {

	public static void main(String... args) {
		SpringApplication.run(LicenciAppApplication.class, args);
	}
}
