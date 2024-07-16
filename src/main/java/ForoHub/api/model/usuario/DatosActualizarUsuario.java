package ForoHub.api.model.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosActualizarUsuario(
        @JsonAlias("nombre_usuario")
        String nombreUsuario,
        String contrasenia
) {
}
