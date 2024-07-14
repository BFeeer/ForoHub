package ForoHub.api.repository;

import ForoHub.api.model.respuesta.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}