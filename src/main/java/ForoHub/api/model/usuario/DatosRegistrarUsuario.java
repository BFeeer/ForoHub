package ForoHub.api.model.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistrarUsuario(
        @JsonAlias("nombre_usuario")
        @NotBlank
        String nombreUsuario,
        @NotBlank
        String contrasenia,
        @JsonAlias("correo_electronico")
        @NotBlank
        String correoElectronico
) {
}
