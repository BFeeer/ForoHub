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

import java.time.LocalDateTime;
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
        // Crear una instancia de tema
        Tema tema = Tema.builder()
                .titulo(datosCrearTema.titulo())
                .descripcion(datosCrearTema.descripcion())
                .usuario(usuario)
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();
        // Guardar el tema en la base de datos
        temaRepository.save(tema);
        // Retornar un DTO con la información del tema creado
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
            // actualizar los datos del tema
            if (datosActualizarTema.titulo() != null){
                tema.setTitulo(datosActualizarTema.titulo());
            }
            if (datosActualizarTema.descripcion() != null){
                tema.setDescripcion(datosActualizarTema.descripcion());
            }
            if ((datosActualizarTema.titulo() != null) || (datosActualizarTema.descripcion() != null) ) {
                tema.setFechaActualizacion(LocalDateTime.now());
            }
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
