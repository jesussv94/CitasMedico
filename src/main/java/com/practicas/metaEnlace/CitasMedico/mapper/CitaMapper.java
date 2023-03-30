package com.practicas.metaEnlace.CitasMedico.mapper;

import com.practicas.metaEnlace.CitasMedico.dto.CitaDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CitaMapper {
    CitaMapper INSTANCE = Mappers.getMapper(CitaMapper.class);
    Cita toCita(CitaDTO citaDTO);
    CitaDTO toCitaDTO(Cita cita);
}
