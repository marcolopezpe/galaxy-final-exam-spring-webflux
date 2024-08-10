package pe.marcolopez.apps.licenciapp.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pe.marcolopez.apps.licenciapp.security.document.UsuarioDocument;
import pe.marcolopez.apps.licenciapp.security.dto.UsuarioDto;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

  @Mapping(target = "contrasena", expression = "java(hashContrasena(usuarioDto.contrasena()))")
  UsuarioDocument dtoToDoc(UsuarioDto usuarioDto);

  UsuarioDto docToDto(UsuarioDocument usuarioDocument);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "contrasena", expression = "java(hashContrasena(usuarioDto.contrasena()))")
  UsuarioDocument dtoToDocUpdate(UsuarioDto usuarioDto, @MappingTarget UsuarioDocument usuarioDocument);

  @Named("hashContrasena")
  default String hashContrasena(String contrasena) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(contrasena);
  }
}
