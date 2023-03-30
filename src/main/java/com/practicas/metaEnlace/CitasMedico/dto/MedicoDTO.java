package com.practicas.metaEnlace.CitasMedico.dto;

import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MedicoDTO {
    private long id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String clave;
    private String numColegiado;
    private List<Long> pacientesId;
    private List<Long> citasId;

    public MedicoDTO(){
        super();
    }
    public MedicoDTO(String numColegiado) {
        super();
        this.numColegiado = numColegiado;
    }
}
