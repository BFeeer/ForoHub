package ForoHub.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacion")
@Tag(name = "Controlador de autenticación", description = "Autenticación de usuarios mediante nombre de usuario y contraseña")
public class AutenticacionController {
    @GetMapping
    public String autenticacion(){
        return "Endpoint para autenticar a un usuario";
    }
}