package com.fogatta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
