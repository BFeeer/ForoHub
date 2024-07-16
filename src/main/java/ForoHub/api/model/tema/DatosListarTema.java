package ForoHub.api.model.tema;

import ForoHub.api.model.usuario.DatosListarUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record DatosListarTema(
        Long id,
        String titulo,
        String descripcion,

        DatosListarUsuario usuario,

        @JsonProperty("fecha_creacion")
        LocalDateTime fechaCreacion,
        @JsonProperty("fecha_actualizacion")
        LocalDateTime fechaActualizacion
) {

    public DatosListarTema(Tema tema){
        this(
                tema.getId(),
                tema.getTitulo(),
                tema.getDescripcion(),
                new DatosListarUsuario(tema.getUsuario()),
                tema.getFechaCreacion(),
                tema.getFechaActualizacion());
    }
}
