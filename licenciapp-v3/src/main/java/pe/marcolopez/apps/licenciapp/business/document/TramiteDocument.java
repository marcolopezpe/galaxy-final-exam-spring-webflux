package pe.marcolopez.apps.licenciapp.business.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TramiteDocument {

  @Field(name = "numero_licencia")
  private String numeroLicencia;

  @Field(name = "clase_categoria")
  private String claseCategoria;

  @Field(name = "tramite")
  private String tramite;

  @Field(name = "fecha_expedicion")
  private String fechaExpedicion;

  @Field(name = "fecha_emision")
  private String fechaEmision;

  @Field(name = "fecha_revalidacion")
  private String fechaRevalidacion;
}
