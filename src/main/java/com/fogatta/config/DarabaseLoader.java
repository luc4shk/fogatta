package com.fogatta.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fogatta.model.Rol;
import com.fogatta.model.Usuario;
import com.fogatta.repositorios.UsuarioRepositorio;

@Configuration
public class DarabaseLoader {

    @Autowired
    private UsuarioRepositorio repo;

    @Bean
    public CommandLineRunner initializeDatabase(){
        return args -> {
            Optional<Usuario> usuario = repo.findById(1);
            Usuario user = usuario.get();
            user.setEnabled(true);
            user.setRol(Rol.ADMIN);

            repo.save(user);
            System.out.println("AYUDAAAAAAAAAAA");

        };
    }
    
}
