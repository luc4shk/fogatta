package com.fogatta.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fogatta.model.Producto;

public interface ProductoRepositorio extends JpaRepository <Producto, Integer> {
    
}
