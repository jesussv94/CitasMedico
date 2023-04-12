package com.practicas.metaEnlace.CitasMedico.controllers;

import com.practicas.metaEnlace.CitasMedico.dto.PacienteDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import com.practicas.metaEnlace.CitasMedico.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity insertar(@RequestBody PacienteDTO pacienteDTO){
        pacienteService.insertarPaciente(pacienteDTO);
        return ResponseEntity.ok("Paciente insertado");
    }
    @PutMapping("/{id}")
    public ResponseEntity editar(@RequestBody PacienteDTO pacienteDTO, @PathVariable Long id){
        pacienteService.editarPaciente(id, pacienteDTO);
        return ResponseEntity.ok("Paciente editado");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        pacienteService.eliminar(id);
        return ResponseEntity.ok("Paciente eliminado");
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
}
