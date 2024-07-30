package pe.marcolopez.apps.licenciapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.marcolopez.apps.licenciapp.document.UsuarioDocument;

@Repository
public interface UsuarioRepository
    extends ReactiveMongoRepository<UsuarioDocument, String> {
}
