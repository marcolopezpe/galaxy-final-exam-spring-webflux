package pe.marcolopez.apps.licenciapp.business.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clientes")
public class ClienteDocument {

  @Id
  private String id;

  @Field(name = "apellidos")
  private String apellidos;

  @Field(name = "nombres")
  private String nombres;

  @Field(name = "numero_documento")
  private String numeroDocumento;

  @Field(name = "email")
  private String email;

  @Field(name = "licencia")
  private LicenciaDocument licencia;

  @Field(name = "puntos_acumulados")
  private String puntosAcumulados;

  @Field(name = "record_numero")
  private String recordNumero;

  @Field(name = "tramites")
  private List<TramiteDocument> tramites = new ArrayList<>();
}
