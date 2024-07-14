package ForoHub.api.service;

import ForoHub.api.model.tema.DatosActualizarTema;
import ForoHub.api.model.tema.DatosCrearTema;
import ForoHub.api.model.tema.DatosListarTema;
import ForoHub.api.model.tema.Tema;
import ForoHub.api.model.usuario.Usuario;
import ForoHub.api.repository.TemaRepository;
import ForoHub.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemaService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TemaRepository temaRepository;

    public DatosListarTema crearTema(DatosCrearTema datosCrearTema){
        // Recuperar los datos del usuario que crea el tema
        Usuario usuario = usuarioRepository.getReferenceById(datosCrearTema.idUsuario());
        // Crear un tema con los datos del usuario
        Tema tema = new Tema(datosCrearTema, usuario);
        // Almacenarlo en la base de datos
        temaRepository.save(tema);
        // retornar un DTO
        return new DatosListarTema(tema);
    }

    public List<DatosListarTema> obtenerTemas() {
        // recuperar todos los temas
        return temaRepository.findAll().stream().map(DatosListarTema::new).toList();
    }

    public DatosListarTema obtenerTemaPorId(Long id){
        // comprobar que el tema existe
        if (temaRepository.existsById(id)){
            // recuperar el tema y retornar un DTO
            return new DatosListarTema(temaRepository.getReferenceById(id));
        }
        return null;
    }

    @Transactional
    public DatosListarTema actualizarTema(Long id, DatosActualizarTema datosActualizarTema){
        // comprobar que el tema existe
        if (temaRepository.existsById(id)){
            // recuperar el tema
            Tema tema = temaRepository.getReferenceById(id);
            // actualizar el tema con los datos nuevos
            tema.actualizar(datosActualizarTema);
            // guardar el tema en la base de datos
            temaRepository.save(tema);
            // retornar un DTO
            return new DatosListarTema(tema);
        }
        return null;
    }

    public void eliminarTema(Long id) {
        // comprobar si existe el tema
        if(temaRepository.existsById(id)){
            // eliminar el tema
            temaRepository.deleteById(id);
        }
    }
}
