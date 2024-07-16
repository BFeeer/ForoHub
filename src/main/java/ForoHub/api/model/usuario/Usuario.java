package ForoHub.api.model.usuario;

import ForoHub.api.model.respuesta.Respuesta;
import ForoHub.api.model.tema.Tema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    private String contrasenia;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    // relaciones
    // un usuario puede crear múltiples temas(tópicos)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tema> temas;
    // un usuario puede tener múltiples respuestas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;


    // constructor
    public Usuario(DatosRegistrarUsuario datosRegistrarUsuario) {
        this.nombreUsuario = datosRegistrarUsuario.nombreUsuario();
        this.contrasenia = datosRegistrarUsuario.contrasenia();
        this.correoElectronico = datosRegistrarUsuario.correoElectronico();
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    // métodos
    public void actualizar(DatosActualizarUsuario datosActualizarUsuario) {
        if (datosActualizarUsuario.nombreUsuario() != null){
            this.nombreUsuario = datosActualizarUsuario.nombreUsuario();
        }
        if (datosActualizarUsuario.contrasenia() != null){
            this.contrasenia = datosActualizarUsuario.contrasenia();
        }
        if ((datosActualizarUsuario.nombreUsuario() != null)||(datosActualizarUsuario.contrasenia() != null)) {
            this.fechaActualizacion = LocalDateTime.now();
        }
    }
}
