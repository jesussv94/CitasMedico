package com.practicas.metaEnlace.CitasMedico.mapper;

import com.practicas.metaEnlace.CitasMedico.dto.MedicoDTO;
import com.practicas.metaEnlace.CitasMedico.entities.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);

    Medico toMedico(MedicoDTO medicoDTO);
    MedicoDTO toMedicoDTO(Medico medico);
}
