package com.practicas.metaEnlace.CitasMedico.services;

import com.practicas.metaEnlace.CitasMedico.dto.PacienteDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import com.practicas.metaEnlace.CitasMedico.entities.Medico;
import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import com.practicas.metaEnlace.CitasMedico.exceptions.DataNotFoundException;
import com.practicas.metaEnlace.CitasMedico.exceptions.DuplicateDataException;
import com.practicas.metaEnlace.CitasMedico.mapper.PacienteMapper;
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
public class PacienteService {

    //Validaciones
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    PacienteMapper pacienteMapper;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private CitaRepository citaRepository;

    public Paciente insertarPaciente(PacienteDTO pacienteDTO) {
        //Validaciones
        Optional<Paciente> pacienteUsuario = pacienteRepository.findByUsuario(pacienteDTO.getUsuario());
        if(pacienteUsuario.isPresent()){
            throw new DuplicateDataException("Nombre de usuario ya existe");
        }
        //Posibilidad de meterlo en directorio utils
        List<Medico> medicos = new ArrayList<>();
        List<Cita> citas = new ArrayList<>();

        if(pacienteDTO.getMedicosId() == null){
            pacienteDTO.setMedicosId(new ArrayList<>());
        }
        if(pacienteDTO.getCitasId() == null){
            pacienteDTO.setCitasId(new ArrayList<>());
        }

        //Codificado de la clave
        String pass = new BCryptPasswordEncoder().encode(pacienteDTO.getClave());

        Paciente paciente = pacienteMapper.toPaciente(pacienteDTO);
        paciente.setClave(pass);
        paciente.setCitas(citas);
        paciente.setMedicos(medicos);
        return pacienteRepository.save(paciente);
    }

    public Paciente editarPaciente(Long id, PacienteDTO pacienteDTO){
        //Validaciones
        Optional<Paciente> pacienteUsuario = pacienteRepository.findByUsuario(pacienteDTO.getUsuario());
        if(pacienteUsuario.isPresent()){
            throw new DuplicateDataException("Nombre de usuario ya existe");
        }

        List<Medico> medicos = new ArrayList<>();
        List<Cita> citas = new ArrayList<>();

        if(pacienteDTO.getMedicosId() == null){
            pacienteDTO.setMedicosId(new ArrayList<>());
        }
        if(pacienteDTO.getCitasId() == null){
            pacienteDTO.setCitasId(new ArrayList<>());
        }

        if(!pacienteDTO.getMedicosId().isEmpty()){
            for(Long medicoId: pacienteDTO.getMedicosId()){
                Optional<Medico> medicoOpt = medicoRepository.findById(medicoId);
                medicoOpt.ifPresent(medicos::add);
            }
        }
        if(pacienteDTO.getCitasId().isEmpty()){
            for (Long CitasId: pacienteDTO.getCitasId()){
                Optional<Cita> citaOpt = citaRepository.findById(CitasId);
                citaOpt.ifPresent(citas::add);
            }
        }

        //Codificado de la clave
        String pass = new BCryptPasswordEncoder().encode(pacienteDTO.getClave());

        Paciente paciente = pacienteMapper.toPaciente(pacienteDTO);
        paciente.setClave(pass);
        paciente.setMedicos(medicos);
        paciente.setCitas(citas);
        paciente.setId(id);
        return pacienteRepository.save(paciente);
    }

    public List<PacienteDTO> listaPacientes(){
        List<PacienteDTO> lista = new ArrayList<>();
        Iterable<Paciente> listaPacientes = pacienteRepository.findAll();
        for(Paciente paciente : listaPacientes){
            PacienteDTO pacienteDTO = pacienteMapper.toPacienteDTO(paciente);
            lista.add(pacienteDTO);
        }
        return lista;
    }

    public PacienteDTO buscar(String usuario){
        Paciente paciente = pacienteRepository.findByUsuario(usuario).orElse(null);
        if(paciente == null){
            throw new DataNotFoundException("Paciente no encontrado");
        }
        return pacienteMapper.toPacienteDTO(paciente);
    }

    public PacienteDTO buscarId(Long id){
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if(paciente == null){
            throw new DataNotFoundException("Paciente no encontrado");
        }
        return pacienteMapper.toPacienteDTO(paciente);
    }

    public void eliminar(Long id){
        Optional<Paciente> pacienteopt = pacienteRepository.findById(id);
        if(pacienteopt.isEmpty()){
            throw new DataNotFoundException("Paciente no encontrado");
        }else{
            pacienteRepository.deleteById(id);
        }
    }
}
