package com.practicas.metaEnlace.CitasMedico.services;

import com.practicas.metaEnlace.CitasMedico.dto.CitaDTO;
import com.practicas.metaEnlace.CitasMedico.dto.MedicoDTO;
import com.practicas.metaEnlace.CitasMedico.dto.PacienteDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import com.practicas.metaEnlace.CitasMedico.entities.Medico;
import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import com.practicas.metaEnlace.CitasMedico.exceptions.DataNotFoundException;
import com.practicas.metaEnlace.CitasMedico.mapper.CitaMapper;
import com.practicas.metaEnlace.CitasMedico.mapper.MedicoMapper;
import com.practicas.metaEnlace.CitasMedico.mapper.PacienteMapper;
import com.practicas.metaEnlace.CitasMedico.repositories.CitaRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.MedicoRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
    private PacienteService pacienteService;
    @Autowired
    private MedicoRepository medicoRepository;

    public Cita insertar(CitaDTO citaDTO){

        Paciente paciente = pacienteRepository.findById(citaDTO.getPacienteId()).orElse(null);
        if(paciente == null){
            throw new DataNotFoundException("Paciente no encontrado");
        }
        Medico medico = medicoRepository.findById(citaDTO.getMedicoId()).orElse(null);
        if(medico == null){
            throw new DataNotFoundException("Médico no encontrado");
        }
        /*
        PacienteDTO pacienteDTO = PacienteMapper.INSTANCE.toPacienteDTO(paciente);
        MedicoDTO medicoDTO = MedicoMapper.INSTANCE.toMedicoDTO(medico);

        pacienteDTO.setMedicosId(Collections.singletonList(medicoDTO.getId()));
        pacienteDTO.setCitasId(Collections.singletonList(citaDTO.getId()));
        pacienteService.editarPaciente(citaDTO.getPacienteId(), pacienteDTO);

        medicoDTO.setPacientesId(Collections.singletonList(pacienteDTO.getId()));
        medicoDTO.setCitasId(Collections.singletonList(citaDTO.getId()));
        */
        Cita cita = citaMapper.toCita(citaDTO);
        cita.setPaciente(paciente);
        cita.setMedico(medico);
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

    public List<CitaDTO> listaCitas(){
        List<CitaDTO> lista = new ArrayList<>();
        Iterable<Cita> listaCitas = citaRepository.findAll();
        for(Cita cita : listaCitas){
            CitaDTO citaDTO = citaMapper.toCitaDTO(cita);
            lista.add(citaDTO);
        }
        return lista;
    }

    public CitaDTO buscarCita(long id){
        Cita cita = citaRepository.findById(id).orElse(null);
        if(cita == null){
            throw new DataNotFoundException("Cita no existe");
        }
        return citaMapper.toCitaDTO(cita);
    }

    public void eliminarCita(long id){
        Optional<Cita> cita = citaRepository.findById(id);
        if(cita.isEmpty()){
            throw new DataNotFoundException("Cita no encontrada");
        }else {
            citaRepository.deleteById(id);
        }
    }
}
