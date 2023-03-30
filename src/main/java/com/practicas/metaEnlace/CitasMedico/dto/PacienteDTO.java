package com.practicas.metaEnlace.CitasMedico.dto;

import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import com.practicas.metaEnlace.CitasMedico.entities.Medico;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PacienteDTO {
    private long id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String clave;
    private List<Long> medicosId;
    private List<Long> citasId;
    private String nss;
    private String numTarjeta;
    private String telefono;
    private String direccion;
}
