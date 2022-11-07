package com.fogatta.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name="mesas")
public class Mesa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer max_ocupantes;
            
    @Column(nullable = false)
    private boolean ocupada;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "mesa_horario", 
        joinColumns = @JoinColumn(name = "mesa_id"),
        inverseJoinColumns = @JoinColumn(name = "horario_id")
    )
    private Set<Horario> productos = new HashSet<>();

    public Mesa() {
    }

    public Mesa(Integer id, Integer max_ocupantes, boolean ocupada) {
        this.id = id;
        this.max_ocupantes = max_ocupantes;
        this.ocupada = ocupada;
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

    public boolean getOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Set<Horario> getProductos() {
        return productos;
    }

    public void setProductos(Set<Horario> productos) {
        this.productos = productos;
    }
    
    
    
}
