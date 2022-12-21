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
import com.fogatta.model.Pedido;
import com.fogatta.model.Producto;
import com.fogatta.service.PedidoServicio;
import com.fogatta.service.UsuarioServicio;
import java.util.ArrayList;

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
    public String viewRealizarPedidosUserPage(Model modelo){

        List<Producto> productos = pedidoServicio.listProductos();
        List<Producto> listaComida = new ArrayList<>();
        List<Producto> listaBebida = new ArrayList<>();

        for(Producto p : productos){
            if(p.getTipo().equals("comida")){
                listaComida.add(p);
            }else{
                listaBebida.add(p);
            }
        }

        modelo.addAttribute("pedido", new Pedido());
        modelo.addAttribute("listaComida", listaComida);
        modelo.addAttribute("listaBebida", listaBebida);

        return "user/formularioPedidos";

    }

    @PostMapping("/user/pedidos/agregar")
    public String savePedido(@ModelAttribute("pedido") Pedido pedido, @AuthenticationPrincipal CustomUserDetails userDetails){

        pedido.setUsuario(userDetails.getUsuario());
        pedidoServicio.guardar(pedido);

        return "redirect:/user/realizarPedidos";


    }
  
}
