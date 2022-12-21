package com.fogatta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fogatta.model.Pedido;
import com.fogatta.model.Producto;
import com.fogatta.repositorios.PedidoRepositorio;
import com.fogatta.repositorios.ProductoRepositorio;

@Service
public class PedidoServicio {

    @Autowired
    private PedidoRepositorio pedidoRepo;

    @Autowired
    private ProductoRepositorio productoRepo;

    public Page<Pedido> listByPage(int numeroPagina, String sortfield, String sortDir){
        Sort sort = Sort.by(sortfield);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(numeroPagina - 1, 5, sort);
        return pedidoRepo.findAll(pageable);
    }

    public List<Pedido> listAll(){
        return pedidoRepo.findAll();
    }

    public void guardar(Pedido pedido){
        pedidoRepo.save(pedido);
    }

    public Pedido getById(Integer id){
        return pedidoRepo.findById(id).get();
    }

    public void delete(Integer id){
        pedidoRepo.deleteById(id);
    }

    public List<Producto> listProductos(){
        return productoRepo.findAll();
    }
    
}
