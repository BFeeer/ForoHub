package ForoHub.api.model.tema;

import ForoHub.api.model.usuario.DatosListarUsuario;

import java.time.LocalDateTime;

public record DatosListarTema(
        Long id,
        String titulo,
        String descripcion,

        DatosListarUsuario usuario,

        LocalDateTime fechaCreacion,
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
