package com.practicas.metaEnlace.CitasMedico.repositories;

import com.practicas.metaEnlace.CitasMedico.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{
    Optional<Medico> findByUsuario(String usuario);
}
