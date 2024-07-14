package ForoHub.api.repository;

import ForoHub.api.model.tema.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {
}