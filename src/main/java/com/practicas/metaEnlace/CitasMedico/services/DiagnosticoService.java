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

import java.util.ArrayList;
import java.util.List;
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
        Cita cita = citaRepository.findById(diagnosticoDTO.getCitaId()).orElse(null);
        if(cita == null){
            throw new DataNotFoundException("Cita no encontrada");
        }
        //Optional<Cita> cita = citaRepository.findById(diagnosticoDTO.getCitaId());
        //if(cita.isEmpty()){
        //    throw new DataNotFoundException("Cita no encontrada");
        //}
        Diagnostico diagnostico = diagnosticoMapper.toDiagnostico(diagnosticoDTO);
        //diagnostico.setCita(cita.get());
        diagnostico.setCita(cita);
        return diagnosticoRepository.save(diagnostico);
    }
    public Diagnostico buscar(long id){
        return diagnosticoRepository.findById(id).orElse(null);
    }

    public List<DiagnosticoDTO> listaDiag(){
        List<DiagnosticoDTO> lista = new ArrayList<>();
        Iterable<Diagnostico> listaDiag = diagnosticoRepository.findAll();
        for(Diagnostico diagnostico : listaDiag){
            DiagnosticoDTO diagnosticoDTO = diagnosticoMapper.toDiagnosticoDTO(diagnostico);
            lista.add(diagnosticoDTO);
        }
        return lista;
    }
    //modificar

    public void eliminar(long id){
        Optional<Diagnostico> diagnostico = diagnosticoRepository.findById(id);
        if(diagnostico.isEmpty()){
            throw new DataNotFoundException("Diagnostico no encontrado");
        } else {
            diagnosticoRepository.deleteById(id);
        }
    }
}
