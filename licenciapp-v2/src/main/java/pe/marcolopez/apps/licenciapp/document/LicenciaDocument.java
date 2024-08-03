package pe.marcolopez.apps.licenciapp.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenciaDocument {

  @Field(name = "clase_categoria")
  private String claseCategoria;

  @Field(name = "numero_licencia")
  private String numeroLicencia;

  @Field(name = "tipo_licencia")
  private String tipoLicencia;

  @Field(name = "fecha_expedicion")
  private String fechaExpedicion;

  @Field(name = "restricciones")
  private String restricciones;

  @Field(name = "vigente_hasta")
  private String vigenteHasta;

  @Field(name = "centro_emision")
  private String centroEmision;
}
