package com.practicas.metaEnlace.CitasMedico.mapper;

import com.practicas.metaEnlace.CitasMedico.dto.DiagnosticoDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Diagnostico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DiagnosticoMapper {
    DiagnosticoMapper INSTANCE = Mappers.getMapper(DiagnosticoMapper.class);
    Diagnostico toDiagnostico(DiagnosticoDTO diagnosticoDTO);
    DiagnosticoDTO toDiagnosticoDTO(Diagnostico diagnostico);
}
