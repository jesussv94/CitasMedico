package com.practicas.metaEnlace.CitasMedico.controllers;

import com.practicas.metaEnlace.CitasMedico.dto.DiagnosticoDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Diagnostico;
import com.practicas.metaEnlace.CitasMedico.services.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diagnostico")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    // Crear diagnóstico
    @PostMapping
    public ResponseEntity insertar(DiagnosticoDTO diagnosticoDTO){
        diagnosticoService.insertar(diagnosticoDTO);
        return ResponseEntity.ok("Diagnótico insertado");
    }
    //Buscar diagnóstico
    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        Diagnostico diagnostico = diagnosticoService.buscar(id);
        return ResponseEntity.ok(diagnostico);
    }
    //Actualizar
    //Eliminar
}
