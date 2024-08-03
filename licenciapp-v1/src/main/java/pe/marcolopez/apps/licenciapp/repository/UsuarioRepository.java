package pe.marcolopez.apps.licenciapp.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.marcolopez.apps.licenciapp.document.UsuarioDocument;
import reactor.core.publisher.Flux;

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
}
