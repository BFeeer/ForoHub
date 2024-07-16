package ForoHub.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {
    @GetMapping
    public String autenticacion(){
        return "Endpoint para autenticar a un usuario";
    }
}