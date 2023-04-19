package com.practicas.metaEnlace.CitasMedico.security;

import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import com.practicas.metaEnlace.CitasMedico.entities.Usuario;
import com.practicas.metaEnlace.CitasMedico.repositories.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final Usuario usuario;

//   @Autowired
//   private PacienteRepository pacienteRepository;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Optional<Paciente> pacienteOpt = pacienteRepository.findByUsuario(usuario.getUsuario());
//
//        if(pacienteOpt.isPresent()){
//            String rol = "PACIENTE";
//            List<GrantedAuthority> authorityList = Collections.singletonList(new SimpleGrantedAuthority(rol));
//        }else {
//            String rol = "MEDICO";
//            List<GrantedAuthority> authorityList = Collections.singletonList(new SimpleGrantedAuthority(rol));
//        }
        return Collections.emptyList();
    }
    @Override
    public String getPassword() {
        return usuario.getClave();
    }

    @Override
    public String getUsername() {
        return usuario.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
