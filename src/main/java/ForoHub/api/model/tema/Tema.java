package ForoHub.api.model.tema;

import ForoHub.api.model.respuesta.Respuesta;
import ForoHub.api.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
}
