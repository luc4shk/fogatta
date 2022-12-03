package com.fogatta.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fogatta.model.Reserva;

public interface ReservasRepo extends JpaRepository<Reserva, Integer>{
    
}
