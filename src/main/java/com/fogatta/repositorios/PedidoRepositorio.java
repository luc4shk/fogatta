package com.fogatta.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fogatta.model.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer>{
    
}
