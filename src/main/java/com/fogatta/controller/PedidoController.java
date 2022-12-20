package com.fogatta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fogatta.details.CustomUserDetails;
import com.fogatta.model.Pedido;
import com.fogatta.service.PedidoServicio;
import com.fogatta.service.UsuarioServicio;

@Controller
public class PedidoController {

    @Autowired
    private PedidoServicio pedidoServicio;

    @Autowired
    private UsuarioServicio userServicio;

    /* USUARIO  */

    /**
     * Método encargado de mostrar la vista de pedidos de usuario
     * @return la plantilla de usuario especificada
     */
    @GetMapping("/user/pedidos")
    public String viewPedidosUserPage(Model modelo, @AuthenticationPrincipal CustomUserDetails userDetails){
        List<Pedido> listaPedidos = userServicio.obtenerPedidos(userDetails.getUsername());
        modelo.addAttribute("listaPedidos", listaPedidos);
        return "user/realizarPedidos";
    }
    
    /* Mapeos de usuario */

    /**
     * Método encargado de mostrar la vista de el para realizar los pedidos
     * @return la plantilla de usuario especificada
     */
    @GetMapping("/user/realizar-pedidos")
    public String viewRealizarPedidosUserPage(){
        return "user/formularioPedidos";
    }

    
}
