package com.practicas.metaEnlace.CitasMedico.services;

import com.practicas.metaEnlace.CitasMedico.dto.MedicoDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import com.practicas.metaEnlace.CitasMedico.entities.Medico;
import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import com.practicas.metaEnlace.CitasMedico.exceptions.DataNotFoundException;
import com.practicas.metaEnlace.CitasMedico.exceptions.DuplicateDataException;
import com.practicas.metaEnlace.CitasMedico.mapper.MedicoMapper;
import com.practicas.metaEnlace.CitasMedico.repositories.CitaRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.MedicoRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        //Validaciones
        Optional<Medico> medicoUsuario = medicoRepository.findByUsuario(medicoDTO.getUsuario());
        if(medicoUsuario.isPresent()){
            throw new DuplicateDataException("Nombre de usuario ya existe");
        }

        List<Cita> citas = new ArrayList<Cita>();
        List<Paciente> pacientes = new ArrayList<Paciente>();

        if(medicoDTO.getPacientesId() == null){
            medicoDTO.setPacientesId(new ArrayList<>());
        }
        if(medicoDTO.getCitasId() == null){
            medicoDTO.setCitasId(new ArrayList<>());
        }

        //Codificado de la clave
        String pass = new BCryptPasswordEncoder().encode(medicoDTO.getClave());

        Medico medico = medicoMapper.toMedico(medicoDTO);
        medico.setClave(pass);
        medico.setPacientes(pacientes);
        medico.setCitas(citas);
        return medicoRepository.save(medico);
    }
    public List<MedicoDTO> listaMedicos() {
        List<MedicoDTO> lista = new ArrayList<>();
        Iterable<Medico> listaMedicos = medicoRepository.findAll();
        for(Medico medico : listaMedicos){
            MedicoDTO medicoDTO = medicoMapper.toMedicoDTO(medico);
            lista.add(medicoDTO);
        }
        return lista;
    }
    public MedicoDTO buscarMedico(String usuario) {
        Medico medico = medicoRepository.findByUsuario(usuario).orElse(null);
        if(medico == null){
            throw new DataNotFoundException("Médico no encontrado");
        }
        return medicoMapper.toMedicoDTO(medico);
    }
    public MedicoDTO buscarMedicoId(long id){
        Medico medico = medicoRepository.findById(id).orElse(null);
        if(medico == null){
            throw new DataNotFoundException("Médico no encontrado");
        }
        return medicoMapper.toMedicoDTO(medico);
    }

    //Editar médico
    public Medico editar(Long id, MedicoDTO medicoDTO){
        //Validaciones
        Optional<Medico> medicoUsuario = medicoRepository.findByUsuario(medicoDTO.getUsuario());
        Optional<Medico> medicoId = medicoRepository.findById(medicoDTO.getId());
        if(medicoUsuario.isPresent() && !medicoId.isPresent()){
            throw new DuplicateDataException("Nombre de usuario ya existe");
        }

        List<Cita> citas = new ArrayList<Cita>();
        List<Paciente> pacientes = new ArrayList<Paciente>();

        if(medicoDTO.getPacientesId() == null){
            medicoDTO.setPacientesId(new ArrayList<>());    //Si medico no tiene pacientes se crea la lista
        }
        if(medicoDTO.getCitasId() == null){
            medicoDTO.setCitasId(new ArrayList<>());
        }

        if(!medicoDTO.getPacientesId().isEmpty()){
            for(Long pacienteId: medicoDTO.getPacientesId()){
                Optional<Paciente> pacienteopt = pacienteRepository.findById(pacienteId);
                pacienteopt.ifPresent(pacientes::add);
            }
        }
        if(medicoDTO.getCitasId().isEmpty()){
            for (Long CitasId: medicoDTO.getCitasId()){
                Optional<Cita> citaOpt = citaRepository.findById(CitasId);
                citaOpt.ifPresent(citas::add);
            }
        }
        //Codificado de la clave
        String pass = new BCryptPasswordEncoder().encode(medicoDTO.getClave());

        Medico medico = medicoMapper.toMedico(medicoDTO);
        medico.setClave(pass);
        medico.setCitas(citas);
        medico.setPacientes(pacientes);
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
