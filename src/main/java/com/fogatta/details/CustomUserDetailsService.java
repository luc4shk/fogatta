package com.fogatta.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fogatta.model.Usuario;
import com.fogatta.repositorios.UsuarioRepositorio;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario user = repo.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("Usuario no encontrado para el correo especificado");
        }

        return new CustomUserDetails(user);
    }
    
}
