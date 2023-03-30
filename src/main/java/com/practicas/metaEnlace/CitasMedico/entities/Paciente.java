package com.practicas.metaEnlace.CitasMedico.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
@Entity(name = "Paciente")
public class Paciente extends Usuario {
    @Column(name= "nss")
    private String nss;
    @Column(name = "numTarjeta")
    private String numTarjeta;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "RelacionMedicoPaciente",
            joinColumns = { @JoinColumn(name = "paciente_id")},
            inverseJoinColumns = { @JoinColumn(name = "medico_id")}
    )
    private List<Medico> medicos;

    @OneToMany(mappedBy="paciente")
    private List<Cita> citas;


    public Paciente(){
        super();
    }

    public Paciente(String nss, String numTarjeta, String telefono, String direccion, List<Medico> medicos) {
        super();
        this.nss = nss;
        this.numTarjeta = numTarjeta;
        this.telefono = telefono;
        this.direccion = direccion;
        this.medicos = medicos;
    }
}
