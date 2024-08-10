package pe.marcolopez.apps.licenciapp.security.document;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class UsuarioDocument implements Serializable {

  @Id
  private String id;

  @Field(name = "apellidos")
  private String apellidos;

  @Field(name = "nombres")
  private String nombres;

  @Field(name = "nombre_usuario")
  private String nombreUsuario;

  @Field(name = "contrasena")
  private String contrasena;

  @Field(name = "email")
  private String email;

  @Field(name = "roles")
  private String[] roles;

  @Field(name = "estado")
  private String estado;
}
