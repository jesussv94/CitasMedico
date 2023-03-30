package com.practicas.metaEnlace.CitasMedico.services;

import com.practicas.metaEnlace.CitasMedico.dto.MedicoDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import com.practicas.metaEnlace.CitasMedico.entities.Medico;
import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import com.practicas.metaEnlace.CitasMedico.exceptions.DataNotFoundException;
import com.practicas.metaEnlace.CitasMedico.mapper.MedicoMapper;
import com.practicas.metaEnlace.CitasMedico.repositories.CitaRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.MedicoRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private MedicoMapper medicoMapper;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private CitaRepository citaRepository;

    public Medico insertarMedico(MedicoDTO medicoDTO) {

        List<Cita> citas = new ArrayList<Cita>();
        List<Paciente> pacientes = new ArrayList<Paciente>();

        if(medicoDTO.getPacientesId() == null){
            medicoDTO.setPacientesId(new ArrayList<>());
        }
        if(medicoDTO.getCitasId() == null){
            medicoDTO.setCitasId(new ArrayList<>());
        }

        if(medicoDTO.getPacientesId().contains(pacientes)){
            for(Long pacienteId: medicoDTO.getPacientesId()){
                Optional<Paciente> pacienteopt = pacienteRepository.findById(pacienteId);
                pacienteopt.ifPresent(pacientes::add);
            }
        }
        if(medicoDTO.getCitasId().contains((citas))){
            for (Long CitasId: medicoDTO.getCitasId()){
                Optional<Cita> citaOpt = citaRepository.findById(CitasId);
                citaOpt.ifPresent(citas::add);
            }
        }

        Medico medico = medicoMapper.toMedico(medicoDTO);
        medico.setPacientes(pacientes);
        medico.setCitas(citas);
        return medicoRepository.save(medico);
    }
    public List<Medico> listaMedicos() {
        return medicoRepository.findAll();
    }
    public Medico buscarMedico(String usuario) {
        return medicoRepository.findByUsuario(usuario).orElse(null);
    }

    //Editar médico
    public Medico editar(Long id, MedicoDTO medicoDTO){
        List<Cita> citas = new ArrayList<Cita>();
        List<Paciente> pacientes = new ArrayList<Paciente>();

        if(medicoDTO.getPacientesId() == null){
            medicoDTO.setPacientesId(new ArrayList<>());    //Si medico no tiene pacientes se crea la lista
        }
        if(medicoDTO.getCitasId() == null){
            medicoDTO.setCitasId(new ArrayList<>());
        }

        if(medicoDTO.getPacientesId().contains(pacientes)){
            for(Long pacienteId: medicoDTO.getPacientesId()){
                Optional<Paciente> pacienteopt = pacienteRepository.findById(pacienteId);
                pacienteopt.ifPresent(pacientes::add);
            }
        }
        if(medicoDTO.getCitasId().contains((citas))){
            for (Long CitasId: medicoDTO.getCitasId()){
                Optional<Cita> citaOpt = citaRepository.findById(CitasId);
                citaOpt.ifPresent(citas::add);
            }
        }
        Medico medico = medicoMapper.toMedico(medicoDTO);
        medico.setId(id);
        return medicoRepository.save(medico);
    }

    public void eliminar(Long id){
        Optional<Medico> medicoOp = medicoRepository.findById(id);
        if(!medicoOp.isPresent()){
            throw new DataNotFoundException("Médico no encontrado");
        }else{
            medicoRepository.deleteById(id);
        }
    }
}
