package com.practicas.metaEnlace.CitasMedico.controllers;

import com.practicas.metaEnlace.CitasMedico.dto.MedicoDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Medico;
import com.practicas.metaEnlace.CitasMedico.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    // Crear Médico:
    @PostMapping
    public ResponseEntity insertar(@RequestBody MedicoDTO medicoDTO){
        medicoService.insertarMedico(medicoDTO);
        return ResponseEntity.ok("200");
    }

    // Actualizar Médico:
    @PutMapping("/update/{id}")
    ResponseEntity editar(@RequestBody MedicoDTO medicoDTO, @PathVariable Long id){
        medicoService.editar(id, medicoDTO);
        return ResponseEntity.ok("200");
    }

    // Borrar Médico:
    @DeleteMapping("/delete/{id}")
    ResponseEntity eliminar(@PathVariable Long id){
        medicoService.eliminar(id);
        return ResponseEntity.ok("200");
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

    @GetMapping("/search/{id}")
    public ResponseEntity<MedicoDTO> buscarId(@PathVariable long id){
        MedicoDTO medico = medicoService.buscarMedicoId(id);
        return ResponseEntity.ok(medico);
    }
}
