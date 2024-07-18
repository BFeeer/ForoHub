package ForoHub.api.service;

import ForoHub.api.model.usuario.DatosActualizarUsuario;
import ForoHub.api.model.usuario.DatosListarUsuario;
import ForoHub.api.model.usuario.DatosRegistrarUsuario;
import ForoHub.api.model.usuario.Usuario;
import ForoHub.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DatosListarUsuario registrarUsuario(DatosRegistrarUsuario datosRegistrarUsuario){
        // Crear una instancia de usuario
        Usuario usuario = Usuario.builder()
                .nombreUsuario(datosRegistrarUsuario.nombreUsuario())
                .contrasenia(passwordEncoder.encode(datosRegistrarUsuario.contrasenia()))
                .correoElectronico(datosRegistrarUsuario.correoElectronico())
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();
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
            if (datosActualizarUsuario.nombreUsuario() != null){
                usuario.setNombreUsuario(datosActualizarUsuario.nombreUsuario());
            }
            if (datosActualizarUsuario.contrasenia() != null){
                usuario.setContrasenia(passwordEncoder.encode(datosActualizarUsuario.contrasenia()));
            }
            if ((datosActualizarUsuario.nombreUsuario() != null)||(datosActualizarUsuario.contrasenia() != null)) {
                usuario.setFechaActualizacion(LocalDateTime.now());
            }
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
