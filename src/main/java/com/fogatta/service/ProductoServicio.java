package com.fogatta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fogatta.model.Producto;
import com.fogatta.repositorios.ProductoRepositorio;

@Service
public class ProductoServicio {


    @Autowired
    private ProductoRepositorio repo;

    public List<Producto> listAll(){
        return repo.findAll();
    }

    public void save(Producto producto){
        repo.save(producto);
    }

    public Producto get(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    
}
