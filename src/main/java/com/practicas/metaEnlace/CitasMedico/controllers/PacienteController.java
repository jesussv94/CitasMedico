package com.practicas.metaEnlace.CitasMedico.controllers;

import com.practicas.metaEnlace.CitasMedico.dto.MedicoDTO;
import com.practicas.metaEnlace.CitasMedico.dto.PacienteDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import com.practicas.metaEnlace.CitasMedico.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity insertar(@RequestBody PacienteDTO pacienteDTO){
        pacienteService.insertarPaciente(pacienteDTO);
        return ResponseEntity.ok("200");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity editar(@RequestBody PacienteDTO pacienteDTO, @PathVariable Long id){
        pacienteService.editarPaciente(id, pacienteDTO);
        return ResponseEntity.ok("200");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        pacienteService.eliminar(id);
        return ResponseEntity.ok("200");
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PacienteDTO>> lista(){
        List<PacienteDTO> pacientes = pacienteService.listaPacientes();
        return ResponseEntity.ok(pacientes);
    }
    @GetMapping("/{usuario}")
    public ResponseEntity buscar(@PathVariable String usuario){
        PacienteDTO pacienteDTO = pacienteService.buscar(usuario);
        return ResponseEntity.ok(pacienteDTO);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<PacienteDTO> buscarId(@PathVariable long id){
        PacienteDTO paciente = pacienteService.buscarId(id);
        return ResponseEntity.ok(paciente);
    }
}
