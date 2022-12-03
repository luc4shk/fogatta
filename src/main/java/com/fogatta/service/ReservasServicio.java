package com.fogatta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fogatta.model.Horario;
import com.fogatta.model.Mesa;
import com.fogatta.model.Reserva;
import com.fogatta.repositorios.HorarioRepositorio;
import com.fogatta.repositorios.MesaRepositorio;
import com.fogatta.repositorios.ReservasRepo;

@Service
public class ReservasServicio {

    @Autowired
    private ReservasRepo repo;

    @Autowired
    private MesaRepositorio mesaRepo;

    @Autowired
    private HorarioRepositorio horarioRepo;

    public List<Reserva> listAll() {
        return repo.findAll();
    }

    public void guardar(Reserva reserva){
        repo.save(reserva);
    }

    public Reserva getById(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public List<Mesa> listMesas(){
        return mesaRepo.findAll();
    }

    public List<Horario> listHoras(){
        return horarioRepo.findAll();
    }
    
}
