package com.practicas.metaEnlace.CitasMedico.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class DiagnosticoDTO {
    private long id;
    private String valoracionEspecialista;
    private String enfermedad;
    private long citaId;
}
