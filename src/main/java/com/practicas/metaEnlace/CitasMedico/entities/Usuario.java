package com.practicas.metaEnlace.CitasMedico.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@Entity
@Table(name = "Usuario")
@Inheritance(strategy=InheritanceType.JOINED)

public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "clave")
    private String clave;
}
