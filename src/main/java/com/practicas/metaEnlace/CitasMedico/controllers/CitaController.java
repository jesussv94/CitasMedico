package com.practicas.metaEnlace.CitasMedico.controllers;

import com.practicas.metaEnlace.CitasMedico.dto.CitaDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import com.practicas.metaEnlace.CitasMedico.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cita")
public class CitaController {
    @Autowired
    private CitaService citaService;

    // Crear cita:
    @PostMapping
    public ResponseEntity insertar(@RequestBody CitaDTO citaDTO){
        citaService.insertar(citaDTO);
        return ResponseEntity.ok("200");
    }

    @GetMapping("/lista")
    public ResponseEntity<List<CitaDTO>> lista(){
        List<CitaDTO> citas = citaService.listaCitas();
        return ResponseEntity.ok(citas);
    }

    @PutMapping("/{id}")
    public ResponseEntity editar(@RequestBody CitaDTO citaDTO, @PathVariable Long id){
        citaService.editarCita(id, citaDTO);
        return ResponseEntity.ok("200");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        citaService.eliminarCita(id);
        return ResponseEntity.ok("200");
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        CitaDTO citaDTO = citaService.buscarCita(id);
        return ResponseEntity.ok(citaDTO);
    }
}
