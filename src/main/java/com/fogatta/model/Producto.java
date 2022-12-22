package com.fogatta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 25)
    private String nombre;

    @Column(nullable = false)
    private int precio;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false, length = 10)
    private String tipo;

    @Column(nullable = false, length = 64)
    private String photo;
    
    /* Constructors */

    public Producto() {
    }

    public Producto(String nombre, int precio, String descripcion, String tipo, String photo) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.photo = photo;
    }

    /* Getters and Setters */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    // @Transient
    // public String getPhotosImagePath(){

    //     if(photo == null || id == null){
    //         return null;
    //     }

    //     return "/product-photos/" + id + "/" + photo;

    // }

    @Override
    public String toString() {
        return nombre;
    }
    
    
    
}
