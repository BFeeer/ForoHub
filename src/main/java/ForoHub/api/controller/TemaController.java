package ForoHub.api.controller;

import ForoHub.api.model.tema.DatosActualizarTema;
import ForoHub.api.model.tema.DatosCrearTema;
import ForoHub.api.model.tema.DatosListarTema;
import ForoHub.api.service.TemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/temas")
public class TemaController {
    @Autowired
    private TemaService temaService;

    @PostMapping
    public ResponseEntity<DatosListarTema> crearTema(@RequestBody @Valid DatosCrearTema datosCrearTema,
                                            UriComponentsBuilder uriComponentsBuilder){
        DatosListarTema datosTema = temaService.crearTema(datosCrearTema);
        // debe retornar un código 201 junto con una URL de acceso al registro y el objeto creado
        URI url = uriComponentsBuilder.path("/temas/{id}").buildAndExpand(datosTema.id()).toUri();
        return ResponseEntity.created(url).body(datosTema);
    }

    @GetMapping
    public ResponseEntity<List<DatosListarTema>> obtenerTemas(){
        List<DatosListarTema> temas = temaService.obtenerTemas();
        // debe retornar un código 200 junto a la información requerida
        return ResponseEntity.ok(temas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListarTema> obtenerTemaPorId(@PathVariable Long id){
        DatosListarTema tema = temaService.obtenerTemaPorId(id);
        // debe retornar un código 200 junto a la información requerida
        return ResponseEntity.ok(tema);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosListarTema> actualizarTema(@PathVariable Long id,@RequestBody DatosActualizarTema datosActualizarTema){
        DatosListarTema temaActualizado = temaService.actualizarTema(id, datosActualizarTema);
        // debe retornar un código 200 junto con el objeto que se actualizó
        return ResponseEntity.ok(temaActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTema(@PathVariable Long id){
        temaService.eliminarTema(id);
        // debe retornar un código 204 sin contenido
        return ResponseEntity.noContent().build();
    }
}