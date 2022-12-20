package com.fogatta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
import com.fogatta.service.UsuarioServicio;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReservaController {

    @Autowired
    private ReservasServicio servicio;

    @Autowired
    private UsuarioServicio userServicio;
    
    /**
     * Método encargado de mostrar la vista de reservas de usuario
     * @return la plantilla de usuario especificada
     */
    @GetMapping("/user/reservas")
    public String viewReservasUserPage(Model modelo, @AuthenticationPrincipal CustomUserDetails userDetails){
        List<Reserva> listaReservas = userServicio.obtenerReservas(userDetails.getUsername());
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


        List<Horario> listaHorarios = servicio.listHoras();
        List<Mesa> listaMesas = servicio.listMesas();

        modelo.addAttribute("listaMesas", listaMesas);
        modelo.addAttribute("listaHorarios", listaHorarios);

        List<Reserva> reservasActivas = servicio.listAll();

        for(Reserva reserve : reservasActivas){

            if(reserve.getEstado().equals("Agendada") && reserve.getMesa().getId() == reserva.getMesa().getId() && reserve.getHorario().getId() == reserva.getHorario().getId()){
                modelo.addAttribute("error", "La mesa seleccionada no se encuentra disponible para el horario solicitado.");
                return "user/formularioReservas";
            }

        }

        reserva.setUsuario(userDetails.getUsuario());
        servicio.guardar(reserva);

        return "redirect:/user/reservas";

    } 
    
    /* ADMINISTRADOR */
    
    /**
     * Método encargado de mostrar la vista de reservas de administrador
     * @param modelo
     * @return la plantilla de administrador especificada
     */
    @GetMapping("/admin/reservas")
    public String viewReservasAdminPage(Model modelo){
        return listByPageAdmin(modelo, 1, "fecha", "asc");
    }


    @GetMapping("/admin/reservas/page/{pageNumber}")
    public String listByPageAdmin(Model modelo, @PathVariable("pageNumber") int paginaActual,
            @Param("sortField") String sortField,
            @Param("sortDir") String sortDir){

        Page<Reserva> page = servicio.listByPage(paginaActual, sortField, sortDir);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Reserva> listaReservas = page.getContent();

        modelo.addAttribute("paginaActual", paginaActual);
        modelo.addAttribute("totalItems", totalItems);
        modelo.addAttribute("totalPages", totalPages);
        modelo.addAttribute("listaReservas", listaReservas);
        modelo.addAttribute("sortField", sortField);
        modelo.addAttribute("sortDir", sortDir);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        modelo.addAttribute("reverseSortDir", reverseSortDir);

        return "admin/reservasAdmin";

    }


    @PostMapping("/admin/reservas/agregar")
    public String saveReservaAdmin(@ModelAttribute("reserva") Reserva reserva, @AuthenticationPrincipal CustomUserDetails userDetails, Model modelo){


        List<Horario> listaHorarios = servicio.listHoras();
        List<Mesa> listaMesas = servicio.listMesas();

        modelo.addAttribute("listaMesas", listaMesas);
        modelo.addAttribute("listaHorarios", listaHorarios);

        List<Reserva> reservasActivas = servicio.listAll();

        for(Reserva reserve : reservasActivas){

            if(reserve.getId() != reserva.getId()){

                if(reserve.getEstado().equals("Agendada") && reserve.getMesa().getId() == reserva.getMesa().getId() && reserve.getHorario().getId() == reserva.getHorario().getId()){
                    modelo.addAttribute("error", "La mesa seleccionada no se encuentra disponible para el horario solicitado.");
                    return "admin/editarReserva";
                }

            }
            else{
                reserva.setFecha_reserva(reserve.getFecha_reserva());
            }

        }

        reserva.setUsuario(userDetails.getUsuario());
        servicio.guardar(reserva);

        return "redirect:/admin/reservas";

    } 
    

    /**
     * Método de permite editar una reserva
     * @param id
     * @return 
     */
    @GetMapping("/admin/reservas/editar/{id}")
    public ModelAndView viewEditarReservaAdminPage(@PathVariable(name ="id") Integer id){
        ModelAndView modelo = new ModelAndView("admin/editarReserva");
        modelo.addObject("reserva", servicio.getById(id));

        List<Horario> listaHorarios = servicio.listHoras();
        List<Mesa> listaMesas = servicio.listMesas();

        modelo.addObject("listaMesas", listaMesas);
        modelo.addObject("listaHorarios", listaHorarios);

        return modelo;
    }


    /**
     * Método encargado de eliminar por id las reservaciones
     * @param id
     * @return 
     */
    @GetMapping("/admin/reservas/eliminar/{id}")
    public String eliminarReserva(@PathVariable(name ="id") Integer id){
        servicio.delete(id);
        return "redirect:/admin/reservas";
    }

}

