package ForoHub.api.service;

import ForoHub.api.model.usuario.DatosActualizarUsuario;
import ForoHub.api.model.usuario.DatosListarUsuario;
import ForoHub.api.model.usuario.DatosRegistrarUsuario;
import ForoHub.api.model.usuario.Usuario;
import ForoHub.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public DatosListarUsuario registrarUsuario(DatosRegistrarUsuario datosRegistrarUsuario){
        // Crear una instancia de usuario
        Usuario usuario = new Usuario(datosRegistrarUsuario);
        // Guardar el usuario en la base de datos
        usuarioRepository.save(usuario);
        // Retornar un DTO con la información del usuario creado
        return new DatosListarUsuario(usuario);
    }

    public List<DatosListarUsuario> obtenerUsuarios() {
        // recuperar todos los usuarios
        return usuarioRepository.findAll().stream().map(DatosListarUsuario::new).toList();
    }

    public DatosListarUsuario obtenerUsuarioPorId(Long id) {
        // comprobar que el usuario existe
        if (usuarioRepository.existsById(id)){
            // recuperar el tema y retornar un DTO
            return new DatosListarUsuario(usuarioRepository.getReferenceById(id));
        }
        return null;
    }

    @Transactional
    public DatosListarUsuario actualizarUsuario(Long id, DatosActualizarUsuario datosActualizarUsuario) {
        // comprobar que el usuario existe
        if (usuarioRepository.existsById(id)){
            // recuperar el usuario
            Usuario usuario = usuarioRepository.getReferenceById(id);
            // actualizar los datos del usuario
            usuario.actualizar(datosActualizarUsuario);
            // guardar el usuario en la base de datos
            usuarioRepository.save(usuario);
            // retornar un DTO
            return new DatosListarUsuario(usuario);
        }
        return null;
    }

    public void eliminarUsuario(Long id) {
        // comprobar que el usuario existe
        if (usuarioRepository.existsById(id)){
            // eliminar el usuario
            usuarioRepository.deleteById(id);
        }
    }
}
