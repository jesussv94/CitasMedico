package com.practicas.metaEnlace.CitasMedico.repositories;

import com.practicas.metaEnlace.CitasMedico.entities.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
    Optional<Diagnostico> findById(long id);
}
