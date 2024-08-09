package pe.marcolopez.apps.licenciapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import pe.marcolopez.apps.licenciapp.document.ClienteDocument;
import pe.marcolopez.apps.licenciapp.document.TramiteDocument;
import pe.marcolopez.apps.licenciapp.dto.ClienteCreateDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteQueryDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteUpdateDto;
import pe.marcolopez.apps.licenciapp.dto.ClienteUpdateLicenciaDto;
import pe.marcolopez.apps.licenciapp.dto.TramiteCreateDto;

import java.util.List;

@Mapper(
    componentModel = "spring"
)
public interface ClienteMapper {


  ClienteDocument dtoToDoc(ClienteCreateDto clienteDto);

  ClienteQueryDto docToDto(ClienteDocument clienteDocument);

  @Mapping(target = "id", ignore = true)
  ClienteDocument dtoToDocUpdate(ClienteUpdateDto clienteUpdateDto,
                                 @MappingTarget ClienteDocument clienteDocument);

  TramiteDocument dtoTramiteToDocTramite(TramiteCreateDto tramiteCreateDto);

  @Mapping(target = "id", source = "clienteDocument.id")
  @Mapping(target = "licencia.claseCategoria", source = "tramiteCreateDto.claseCategoria")
  @Mapping(target = "licencia.numeroLicencia", source = "tramiteCreateDto.numeroLicencia")
  @Mapping(target = "licencia.tipoLicencia", source = "tramiteCreateDto.tipoLicencia")
  @Mapping(target = "licencia.fechaExpedicion", source = "tramiteCreateDto.fechaExpedicion")
  @Mapping(target = "licencia.restricciones", source = "tramiteCreateDto.restricciones")
  @Mapping(target = "licencia.vigenteHasta", source = "tramiteCreateDto.fechaRevalidacion")
  @Mapping(target = "licencia.centroEmision", source = "tramiteCreateDto.centroEmision")
  @Mapping(target = "tramites", expression = "java(addTramite(tramiteCreateDto, clienteDocument))")
  ClienteDocument tramiteDtoToDoc(ClienteDocument clienteDocument,
                                  TramiteCreateDto tramiteCreateDto);

  @Named("addTramite")
  default List<TramiteDocument> addTramite(TramiteCreateDto tramiteCreateDto,
                                           ClienteDocument clienteDocument) {
    clienteDocument.getTramites().add(dtoTramiteToDocTramite(tramiteCreateDto));
    return clienteDocument.getTramites();
  }

  @Mapping(target = "id", source = "clienteDocument.id")
  @Mapping(target = "tramites", ignore = true)
  @Mapping(target = "licencia", source = "clienteDocument.licencia")
  @Mapping(target = "puntosAcumulados", source = "clienteUpdateLicenciaDto.puntosAcumulados")
  @Mapping(target = "recordNumero", source = "clienteDocument.recordNumero")
  @Mapping(target = "licencia.tipoLicencia", source = "clienteUpdateLicenciaDto.licencia.tipoLicencia")
  @Mapping(target = "licencia.restricciones", source = "clienteUpdateLicenciaDto.licencia.restricciones")
  ClienteDocument licenciaUpdateDtoToDoc(@MappingTarget ClienteDocument clienteDocumentTarget,
                                         ClienteDocument clienteDocument,
                                         ClienteUpdateLicenciaDto clienteUpdateLicenciaDto);
}
