package com.practicas.metaEnlace.CitasMedico.repositories;

import com.practicas.metaEnlace.CitasMedico.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    Optional<Cita> findById(long id);
}
