package ForoHub.api.service;

import ForoHub.api.model.usuario.Usuario;
import ForoHub.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // definición de un contenedor de la información de autenticación del usuario
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // comprobar si existe el nombre de usuario en la base de datos
        Optional<Usuario> posibleUsuario = usuarioRepository.findByNombreUsuario(username);
        if (posibleUsuario.isPresent()){
            // recuperar el usuario
            Usuario usuario = posibleUsuario.get();
            // retornar un objeto UserDetails
            return User.builder()
                    .username(usuario.getNombreUsuario())
                    .password(usuario.getContrasenia())
                    .roles("USER")
                    .build();
        }else{
            throw new UsernameNotFoundException(username);
        }
    }
}
