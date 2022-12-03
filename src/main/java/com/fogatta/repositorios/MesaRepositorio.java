package com.fogatta.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fogatta.model.Mesa;

public interface MesaRepositorio extends JpaRepository<Mesa, Integer>{
    
}
