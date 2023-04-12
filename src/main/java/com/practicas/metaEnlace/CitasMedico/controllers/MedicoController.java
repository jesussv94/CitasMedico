package com.practicas.metaEnlace.CitasMedico.controllers;

import com.practicas.metaEnlace.CitasMedico.dto.MedicoDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Medico;
import com.practicas.metaEnlace.CitasMedico.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    // Crear Médico:
    @PostMapping
    public ResponseEntity insertar(@RequestBody MedicoDTO medicoDTO){
        medicoService.insertarMedico(medicoDTO);
        return ResponseEntity.ok("Medico insertado");
    }

    // Actualizar Médico:
    @PutMapping("/{id}")
    ResponseEntity editar(@RequestBody MedicoDTO medicoDTO, @PathVariable Long id){
        medicoService.editar(id, medicoDTO);
        return ResponseEntity.ok("Médico editado");
    }

    // Borrar Médico:
    @DeleteMapping("/{id}")
    ResponseEntity eliminar(@PathVariable Long id){
        medicoService.eliminar(id);
        return ResponseEntity.ok("Médico eliminado");
    }

    // Lista de médicos:
    @GetMapping("/lista")
    public ResponseEntity<List<MedicoDTO>> lista(){
        List<MedicoDTO> medicos = medicoService.listaMedicos();
        return ResponseEntity.ok(medicos);
    }

    // Buscar médico por usuario
    @GetMapping("/{usuario}")
    public ResponseEntity<MedicoDTO> buscar(@PathVariable String usuario){
        MedicoDTO medico = medicoService.buscarMedico(usuario);
        return ResponseEntity.ok(medico);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<MedicoDTO> buscarId(@PathVariable long id){
        MedicoDTO medico = medicoService.buscarMedicoId(id);
        return ResponseEntity.ok(medico);
    }
}
