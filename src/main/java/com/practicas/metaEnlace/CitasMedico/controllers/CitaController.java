package com.practicas.metaEnlace.CitasMedico.controllers;

import com.practicas.metaEnlace.CitasMedico.dto.CitaDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import com.practicas.metaEnlace.CitasMedico.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    private CitaService citaService;

    // Crear cita:
    @PostMapping
    public ResponseEntity insertar(@RequestBody CitaDTO citaDTO){
        citaService.insertar(citaDTO);
        return ResponseEntity.ok("Cita insertada");
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Cita>> lista(){
        List<Cita> citas = citaService.listaCitas();
        return ResponseEntity.ok(citas);
    }

    @PutMapping("/{id}")
    public ResponseEntity editar(@RequestBody CitaDTO citaDTO, @PathVariable Long id){
        citaService.editarCita(id, citaDTO);
        return ResponseEntity.ok("Cita editada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        citaService.eliminarCita(id);
        return ResponseEntity.ok("Cita eliminada");
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        Cita cita = citaService.buscarCita(id);
        return ResponseEntity.ok(cita);
    }
    //Eliminar
    //Actualizar
}
