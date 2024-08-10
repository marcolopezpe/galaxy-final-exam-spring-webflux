package pe.marcolopez.apps.licenciapp.security.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.marcolopez.apps.licenciapp.security.document.UsuarioDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UsuarioRepository
    extends ReactiveMongoRepository<UsuarioDocument, String> {

  @Query("""
    {
      $or: [
        {'nombre_usuario':  {$regex: '?0'}},
        {'email':  {$regex: '?1'}},
      ]
    }
  """)
  Flux<UsuarioDocument> findAllByFilter(String nombreUsuario, String email);

  Mono<UsuarioDocument> findTop1ByNombreUsuarioIgnoreCase(String usuario);
}
