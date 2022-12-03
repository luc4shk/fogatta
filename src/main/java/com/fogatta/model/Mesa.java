package com.fogatta.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="mesas")
public class Mesa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private Integer max_ocupantes;
            
    public Mesa() {
    }

    public Mesa(Integer id, Integer max_ocupantes, String nombre) {
        this.id = id;
        this.max_ocupantes = max_ocupantes;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMax_ocupantes() {
        return max_ocupantes;
    }

    public void setMax_ocupantes(Integer max_ocupantes) {
        this.max_ocupantes = max_ocupantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
