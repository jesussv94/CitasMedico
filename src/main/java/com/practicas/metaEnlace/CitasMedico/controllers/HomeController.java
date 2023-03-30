package com.practicas.metaEnlace.CitasMedico.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/")
    String home(){
        return "Endpoint: \"/medico/\",  " +
                "Endpoint: \"/paciente\" " +
                "Endpoint: \"/cita\" " +
                "Endpoint: \"/diagnostico\" ";
    }
}
