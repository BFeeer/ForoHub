package ForoHub.api.controller;

import ForoHub.api.model.usuario.DatosActualizarUsuario;
import ForoHub.api.model.usuario.DatosListarUsuario;
import ForoHub.api.model.usuario.DatosRegistrarUsuario;
import ForoHub.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Controlador de usuarios", description = "Operaciones de inserción, lectura, actualización y eliminación de usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Registrar usuario", description = "Registra la información de un usuario")
    public ResponseEntity<DatosListarUsuario> registrarUsuario(@RequestBody @Valid DatosRegistrarUsuario datosRegistrarUsuario,
                                                               UriComponentsBuilder uriComponentsBuilder){
        // crear un usuario con los datos recibidos en el cuerpo de la solicitud
        DatosListarUsuario datosUsuario = usuarioService.registrarUsuario(datosRegistrarUsuario);
        // debe retornar un código 201 junto con una URL de acceso al registro y el objeto creado
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(datosUsuario.id()).toUri();
        return ResponseEntity.created(url).body(datosUsuario);
    }

    @GetMapping
    @Operation(summary = "Obtener usuarios", description = "Obtiene la información de todos los usuarios")
    public ResponseEntity<List<DatosListarUsuario>> obtenerUsuarios(){
        List<DatosListarUsuario> usuarios = usuarioService.obtenerUsuarios();
        // debe retornar un código 200 junto con la información requerida
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario", description = "Obtiene la información de un único usuario mediante su identificador")
    public ResponseEntity<DatosListarUsuario> obtenerUsuarioPorId(@PathVariable Long id){
        DatosListarUsuario usuario = usuarioService.obtenerUsuarioPorId(id);
        // debe retornar un código 200 junto con la información requerida
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario (nombre de usuario y/o contraseña) mediante su identificador")
    public ResponseEntity<DatosListarUsuario> actualizarUsuario(@PathVariable Long id, @RequestBody DatosActualizarUsuario datosActualizarUsuario){
        DatosListarUsuario usuarioActualizado = usuarioService.actualizarUsuario(id, datosActualizarUsuario);
        // debe retornar un código 200 junto con la información actualizada
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina la información de un usuario mediante su identificador (id)")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        // debe retornar un código 204 sin contenido
        return ResponseEntity.noContent().build();
    }
}