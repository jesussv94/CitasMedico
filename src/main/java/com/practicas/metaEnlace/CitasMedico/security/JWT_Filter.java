package com.practicas.metaEnlace.CitasMedico.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWT_Filter extends OncePerRequestFilter {
    @Autowired
    private UsuarioDetailsService usuarioDetailsService;
    @Autowired
    private JWT_Util jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String autorizationHeader = request.getHeader("Authorization");

        if(autorizationHeader != null){
            String jwt = autorizationHeader.substring(7);

            String username = jwtUtil.validarToken(jwt);
            UserDetails userDetails = usuarioDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken autentification = new UsernamePasswordAuthenticationToken(
                    username, userDetails.getPassword(), userDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(autentification);
        }
        filterChain.doFilter(request, response);
    }
}
