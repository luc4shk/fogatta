package com.fogatta.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fogatta.model.Horario;

public interface HorarioRepositorio extends JpaRepository<Horario, Integer>{
    
}
