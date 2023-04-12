package com.practicas.metaEnlace.CitasMedico.security;

import com.practicas.metaEnlace.CitasMedico.entities.Usuario;
import com.practicas.metaEnlace.CitasMedico.repositories.MedicoRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.PacienteRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class UsuarioDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(username).orElse(null);
        var rol = "";
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        if(pacienteRepository.findByUsuario(username).isPresent()){
            rol = "PACIENTE";
        } else{
            rol = "MEDICO";
        }
        return new User(username, usuario.getClave(), Collections.singletonList(new SimpleGrantedAuthority(rol)));
    }
}
