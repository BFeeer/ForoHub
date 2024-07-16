package ForoHub.api.model.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record DatosListarUsuario(
        Long id,
        @JsonProperty("nombre_usuario")
        String nombreUsuario,
        @JsonProperty("correo_electronico")
        String correoElectronico,
        @JsonProperty("fecha_creacion")
        LocalDateTime fechaCreacion,
        @JsonProperty("fecha_actualizacion")
        LocalDateTime fechaActualizacion

) {
    public DatosListarUsuario(Usuario usuario){
        this(
               usuario.getId(),
               usuario.getNombreUsuario(),
               usuario.getCorreoElectronico(),
               usuario.getFechaCreacion(),
               usuario.getFechaActualizacion()
        );
    }
}
