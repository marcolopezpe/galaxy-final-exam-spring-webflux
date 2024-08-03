package pe.marcolopez.apps.licenciapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.marcolopez.apps.licenciapp.document.ClienteDocument;

@Repository
public interface ClienteRepository
    extends ReactiveMongoRepository<ClienteDocument, String> {
}
