package com.practicas.metaEnlace.CitasMedico.services;

import com.practicas.metaEnlace.CitasMedico.dto.CitaDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import com.practicas.metaEnlace.CitasMedico.entities.Medico;
import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import com.practicas.metaEnlace.CitasMedico.exceptions.DataNotFoundException;
import com.practicas.metaEnlace.CitasMedico.mapper.CitaMapper;
import com.practicas.metaEnlace.CitasMedico.repositories.CitaRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.MedicoRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private CitaMapper citaMapper;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    public Cita insertar(CitaDTO citaDTO){

        Optional<Paciente> pacienteOpt = pacienteRepository.findById(citaDTO.getPacienteId());
        if(pacienteOpt.isEmpty()){
            throw new DataNotFoundException("Paciente no encontrado");
        }
        Optional<Medico> medicoOpt = medicoRepository.findById(citaDTO.getMedicoId());
        if(medicoOpt.isEmpty()){
            throw new DataNotFoundException("Médico no encontrado");
        }

        Cita cita = citaMapper.toCita(citaDTO);
        //cita.setPaciente(pacienteOpt.get());
        //cita.setMedico(medicoOpt.get());
        return citaRepository.save(cita);
    }

    public Cita editarCita(Long id, CitaDTO citaDTO){
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(citaDTO.getPacienteId());
        if(pacienteOpt.isEmpty()){
            throw new DataNotFoundException("Paciente no encontrado");
        }
        Optional<Medico> medicoOpt = medicoRepository.findById(citaDTO.getMedicoId());
        if(medicoOpt.isEmpty()){
            throw new DataNotFoundException("Médico no encontrado");
        }
        Cita cita = citaMapper.toCita(citaDTO);
        //cita.setPaciente(pacienteOpt.get());
        //cita.setMedico(medicoOpt.get());
        cita.setId(id);
        return citaRepository.save(cita);
    }

    public List<Cita> listaCitas(){
        return citaRepository.findAll();
    }
    public Cita buscarCita(long id){
        return citaRepository.findById(id).orElse(null);
    }
    public void eliminarCita(long id){
        Optional<Cita> citaopt = citaRepository.findById(id);
        if(citaopt.isEmpty()){
            throw new DataNotFoundException("Cita no encontrada");
        }else {
            citaRepository.deleteById(id);
        }
    }
}
