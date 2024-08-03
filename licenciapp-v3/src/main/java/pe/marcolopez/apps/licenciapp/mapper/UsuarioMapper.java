package pe.marcolopez.apps.licenciapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.marcolopez.apps.licenciapp.document.UsuarioDocument;
import pe.marcolopez.apps.licenciapp.dto.UsuarioDto;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

  UsuarioDocument dtoToDoc(UsuarioDto usuarioDto);

  UsuarioDto docToDto(UsuarioDocument usuarioDocument);

  @Mapping(target = "id", ignore = true)
  UsuarioDocument dtoToDocUpdate(UsuarioDto usuarioDto, @MappingTarget UsuarioDocument usuarioDocument);
}
