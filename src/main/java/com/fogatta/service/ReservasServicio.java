package com.fogatta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Reserva> listByPage(int numeroPagina, String sortField, String sortDir){
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(numeroPagina - 1, 5, sort);
        return repo.findAll(pageable);
    }

    public List<Reserva> listAll(){
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
