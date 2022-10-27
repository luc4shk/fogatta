package com.fogatta.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fogatta.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    
    public Usuario findByEmail(String email);

}
