package com.fogatta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fogatta.model.Producto;
import com.fogatta.repositorios.ProductoRepositorio;

@Service
public class ProductoServicio {


    @Autowired
    private ProductoRepositorio repo;

    public Page<Producto> listAll(int numeroPagina, String sortfield, String sortDir){
        Sort sort = Sort.by(sortfield);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(numeroPagina - 1, 6, sort);
        return repo.findAll(pageable);
    }

    public Producto save(Producto producto){
        return repo.save(producto);
    }

    public Producto get(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    
}
