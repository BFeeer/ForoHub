package ForoHub.api.model.usuario;

public record DatosListarUsuario(
        Long id,
        String nombreUsuario,
        String correoElectronico

) {
    public DatosListarUsuario(Usuario usuario){
        this(
               usuario.getId(),
               usuario.getNombreUsuario(),
               usuario.getCorreoElectronico()
        );
    }
}
