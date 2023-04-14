package com.practicas.metaEnlace.CitasMedico.security;

import com.practicas.metaEnlace.CitasMedico.entities.Medico;
import com.practicas.metaEnlace.CitasMedico.entities.Paciente;
import com.practicas.metaEnlace.CitasMedico.entities.Usuario;
import com.practicas.metaEnlace.CitasMedico.repositories.MedicoRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.PacienteRepository;
import com.practicas.metaEnlace.CitasMedico.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsuario(username);
        Optional<Paciente> pacienteOpt = pacienteRepository.findByUsuario(username);
        Optional<Medico> medicoOpt = medicoRepository.findByUsuario(username);
        String rol = null;

        if(usuarioOpt.isPresent()){
            Usuario usuario = usuarioOpt.get();
            return new UserDetailsImpl(usuario);
        }
//        if(pacienteOpt.isPresent()){
//            Paciente paciente = pacienteOpt.get();
//            rol = "PACIENTE";
//            return new User(paciente.getUsuario(),
//                            paciente.getClave(),
//                            Collections.singletonList(new SimpleGrantedAuthority(rol)));
//        }
//        if(medicoOpt.isPresent()) {
//            Medico medico = medicoOpt.get();
//            rol = "MEDICO";
//            return new User(medico.getUsuario(),
//                            medico.getClave(),
//                            Collections.singletonList(new SimpleGrantedAuthority(rol)));
//        }
        throw new UsernameNotFoundException("Usuario no encontrado");
    }
}
