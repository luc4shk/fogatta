package com.fogatta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fogatta.details.CustomUserDetails;
import com.fogatta.model.Horario;
import com.fogatta.model.Mesa;
import com.fogatta.model.Reserva;
import com.fogatta.service.ReservasServicio;

@Controller
public class ReservaController {

    @Autowired
    private ReservasServicio servicio;

    /**
     * Método encargado de mostrar la vista de reservas de usuario
     * @return la plantilla de usuario especificada
     */
    @GetMapping("/user/reservas")
    public String viewReservasUserPage(Model modelo){

        List<Reserva> listaReservas = servicio.listAll();
        modelo.addAttribute("listaReservas", listaReservas);
        return "user/realizarReservas";
    }

    /**
     * Método encargado de mostrar la vista de el para realizar las reservas
     * @return la plantilla de usuario especificada
     */
    @GetMapping("/user/realizar-reservas")
    public String viewRealizarReservasUserPage(Model modelo){

        List<Horario> listaHorarios = servicio.listHoras();
        List<Mesa> listaMesas = servicio.listMesas();

        modelo.addAttribute("reserva", new Reserva());
        modelo.addAttribute("listaMesas", listaMesas);
        modelo.addAttribute("listaHorarios", listaHorarios);

        return "user/formularioReservas";
    }

    @PostMapping("/user/reservas/agregar")
    public String saveReserva(@ModelAttribute("reserva") Reserva reserva, @AuthenticationPrincipal CustomUserDetails userDetails, Model modelo){


        List<Reserva> reservasActivas = servicio.listAll();

        for(Reserva reserve : reservasActivas){

            if(reserve.getEstado().equals("Agendada") && reserve.getMesa().getId() == reserva.getMesa().getId() && reserve.getHorario().getId() == reserva.getHorario().getId()){
                modelo.addAttribute("error", "La mesa seleccionada no se encuentra disponible para el horario solicitado");
                return "user/formularioReservas";
            }

        }

        reserva.setUsuario(userDetails.getUsuario());
        servicio.guardar(reserva);

        return "redirect:/user/reservas";

    }
    
}
