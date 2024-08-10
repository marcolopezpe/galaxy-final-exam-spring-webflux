package pe.marcolopez.apps.licenciapp.business.util;

import pe.marcolopez.apps.licenciapp.business.dto.ClienteCreateDto;
import pe.marcolopez.apps.licenciapp.business.dto.ClienteUpdateDto;

public class PayloadUtil {

  public static ClienteCreateDto dtoToCreate() {
    return ClienteCreateDto.builder()
        .apellidos("APELLIDO TEST")
        .nombres("NOMBRE TEST")
        .numeroDocumento("46491904")
        .email("ELCORREOTEST@OUTLOOK.COM")
        .puntosAcumulados("0")
        .recordNumero("982349832948392")
        .build();
  }

  public static ClienteUpdateDto dtoToUpdate() {
    return ClienteUpdateDto.builder()
        .apellidos("APELLIDO TEST UPDATED")
        .nombres("NOMBRE TEST UPDATED")
        .numeroDocumento("46491904")
        .email("ELCORREOACTUALIZADO@OUTLOOK.COM")
        .puntosAcumulados("0")
        .recordNumero("453432356")
        .build();
  }

  public static ClienteCreateDto dtoToCreateInvalid() {
    return ClienteCreateDto.builder()
        .apellidos("")
        .nombres("NOMBRE TEST")
        .numeroDocumento("46491904")
        .email("")
        .puntosAcumulados("0")
        .recordNumero("982349832948392")
        .build();
  }
}
