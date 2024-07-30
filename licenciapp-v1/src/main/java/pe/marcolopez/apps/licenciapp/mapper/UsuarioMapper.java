package pe.marcolopez.apps.licenciapp.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.licenciapp.document.UsuarioDocument;
import pe.marcolopez.apps.licenciapp.dto.UsuarioDto;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

  UsuarioDocument dtoToDoc(UsuarioDto usuarioDto);

  UsuarioDto docToDto(UsuarioDocument usuarioDocument);
}
