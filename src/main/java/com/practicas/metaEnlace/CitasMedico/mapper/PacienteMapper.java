package com.practicas.metaEnlace.CitasMedico.mapper;

import com.practicas.metaEnlace.CitasMedico.dto.PacienteDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);
    Paciente toPaciente(PacienteDTO pacienteDTO);
    PacienteDTO toPacienteDTO(Paciente paciente);
}
