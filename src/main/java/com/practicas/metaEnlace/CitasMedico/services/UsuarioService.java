package com.practicas.metaEnlace.CitasMedico.services;

import com.practicas.metaEnlace.CitasMedico.entities.Usuario;
import com.practicas.metaEnlace.CitasMedico.exceptions.DataNotFoundException;
import com.practicas.metaEnlace.CitasMedico.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarUsuario(String usuario){
        Usuario buscarUsuario = usuarioRepository.findByUsuario(usuario).orElse(null);
        if(buscarUsuario == null){
            throw new DataNotFoundException("Usuario no encontrado");
        }
        return buscarUsuario;
    }
}
