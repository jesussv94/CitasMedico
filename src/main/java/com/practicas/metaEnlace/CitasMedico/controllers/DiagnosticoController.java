package com.practicas.metaEnlace.CitasMedico.controllers;

import com.practicas.metaEnlace.CitasMedico.dto.CitaDTO;
import com.practicas.metaEnlace.CitasMedico.dto.DiagnosticoDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Diagnostico;
import com.practicas.metaEnlace.CitasMedico.services.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/diagnostico")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping("/lista")
    public ResponseEntity<List<DiagnosticoDTO>> lista(){
        List<DiagnosticoDTO> diagnosticos = diagnosticoService.listaDiag();
        return ResponseEntity.ok(diagnosticos);
    }

    // Crear diagnóstico
    @PostMapping
    public ResponseEntity insertar(DiagnosticoDTO diagnosticoDTO){
        diagnosticoService.insertar(diagnosticoDTO);
        return ResponseEntity.ok("200");
    }
    //Buscar diagnóstico
    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        Diagnostico diagnostico = diagnosticoService.buscar(id);
        return ResponseEntity.ok(diagnostico);
    }

    //Actualizar

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        diagnosticoService.eliminar(id);
        return ResponseEntity.ok("200");
    }
}
