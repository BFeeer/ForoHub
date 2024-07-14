package ForoHub.api.model.tema;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCrearTema(
        @NotBlank
        String titulo,
        @NotBlank
        String descripcion,
        @NotNull
        @JsonAlias("id_usuario")
        Long idUsuario
) {
}
