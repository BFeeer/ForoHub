package ForoHub.api.model.tema;

import ForoHub.api.model.respuesta.Respuesta;
import ForoHub.api.model.usuario.Usuario;
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
@Table(name = "temas")
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    @ManyToOne(fetch = FetchType.LAZY)
    // indica el campo que contiene la llave foránea
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    // relaciones
    // un tema puede tener múltiples respuestas
    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

    // constructor
    public Tema(DatosCrearTema datosCrearTema, Usuario usuario) {
        this.titulo = datosCrearTema.titulo();
        this.descripcion = datosCrearTema.descripcion();
        this.usuario = usuario;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    // métodos
    public void actualizar(DatosActualizarTema datosActualizarTema){
        if (datosActualizarTema.titulo() != null){
            this.titulo = datosActualizarTema.titulo();
        }
        if (datosActualizarTema.descripcion() != null){
            this.descripcion = datosActualizarTema.descripcion();
        }
        if ((datosActualizarTema.titulo() != null) || (datosActualizarTema.descripcion() != null) ) {
            this.fechaActualizacion = LocalDateTime.now();
        }
    }
}
