package com.fogatta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fogatta.model.Usuario;
import com.fogatta.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio repo;

    public void register(Usuario usuario){
        
        encondedPassword(usuario);

        repo.save(usuario);

    }

    private void encondedPassword(Usuario usuario){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

    }
    
}
