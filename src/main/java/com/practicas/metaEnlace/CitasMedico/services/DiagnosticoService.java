package com.practicas.metaEnlace.CitasMedico.services;

import com.practicas.metaEnlace.CitasMedico.dto.DiagnosticoDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import com.practicas.metaEnlace.CitasMedico.entities.Diagnostico;
import com.practicas.metaEnlace.CitasMedico.exceptions.DataNotFoundException;
import com.practicas.metaEnlace.CitasMedico.mapper.DiagnosticoMapper;
import com.practicas.metaEnlace.CitasMedico.repositories.CitaRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiagnosticoService {
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private DiagnosticoMapper diagnosticoMapper;
    @Autowired
    private CitaRepository citaRepository;

    public Diagnostico insertar(DiagnosticoDTO diagnosticoDTO){
        //Busca la id de la cita
        Optional<Cita> cita = citaRepository.findById(diagnosticoDTO.getCitaId());
        if(cita.isEmpty()){
            throw new DataNotFoundException("Cita no encontrada");
        }
        Diagnostico diagnostico = diagnosticoMapper.toDiagnostico(diagnosticoDTO);
        diagnostico.setCita(cita.get());
        return diagnosticoRepository.save(diagnostico);
    }
    public Diagnostico buscar(long id){
        return diagnosticoRepository.findById(id).orElse(null);
    }
    //modificar
    //Eliminar
}
