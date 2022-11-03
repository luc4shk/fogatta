package com.fogatta.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fogatta.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    public Usuario findByEmail(String email);

    @Query("UPDATE Usuario u SET u.enabled = true WHERE u.id = ?1")
    @Modifying
    public void enable(Integer id);

    @Query("SELECT u FROM Usuario u WHERE u.verificationCode = ?1")
    public Usuario findByVerificationCode(String code);

    public Usuario findByResetPasswordToken(String token);

}
