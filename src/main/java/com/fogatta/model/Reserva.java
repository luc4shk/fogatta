package com.fogatta.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name= "reservas" )
public class Reserva{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer acompanantes;
    
    @Column(nullable = false, length=20)
    private String estado;
    
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDateTime fecha;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

    
    /*Constructor*/

    public Reserva() {
    }

    public Reserva(Integer id, Integer acompanantes, String estado, LocalDateTime fecha_reserva) {
        this.id = id;
        this.acompanantes = acompanantes;
        this.estado = estado;
        this.fecha = fecha_reserva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAcompanantes() {
        return acompanantes;
    }

    public void setAcompanantes(Integer acompanantes) {
        this.acompanantes = acompanantes;
    }

    public String getEstado() {
        return estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha_reserva() {
        return fecha;
    }

    public void setFecha_reserva(LocalDateTime fecha_reserva) {
        this.fecha = fecha_reserva;
    }

    @PrePersist
    public void asignarFecha_reserva(){
        fecha = LocalDateTime.now();
        estado = "Agendada";
    }
    
}
