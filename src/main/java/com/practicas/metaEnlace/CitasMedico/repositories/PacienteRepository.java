package com.practicas.metaEnlace.CitasMedico.repositories;

import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByUsuario(String usuario);
    List<Paciente> findAll();
}
