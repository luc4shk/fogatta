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
import org.springframework.web.servlet.ModelAndView;

import com.fogatta.details.CustomUserDetails;
import com.fogatta.model.Pedido;
import com.fogatta.model.Producto;
import com.fogatta.service.PedidoServicio;
import com.fogatta.service.UsuarioServicio;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String savePedido(@ModelAttribute("pedido") Pedido pedido, @AuthenticationPrincipal CustomUserDetails userDetails, Model modelo){

        List<Producto> productos = pedidoServicio.listProductos();
        List<Producto> listaComida = new ArrayList<>();
        List<Producto> listaBebida = new ArrayList<>();
        List<Pedido> pedidos = pedidoServicio.listAll();

        for(Producto p : productos){
            if(p.getTipo().equals("comida")){
                listaComida.add(p);
            }else{
                listaBebida.add(p);
            }
        }

        modelo.addAttribute("listaComida", listaComida);
        modelo.addAttribute("listaBebida", listaBebida);

        /*for (Producto p : pedido.getProductos()){

            if(p != null){
                pedido.setUsuario(userDetails.getUsuario());
                pedidoServicio.guardar(pedido);
                return "redirect:/user/pedidos";
            }

        }*/

        Producto [] products = (Producto[]) pedido.getProductos().toArray();

        if( products.length == 1 && products[0] == null ){
            modelo.addAttribute("error", "Debe seleccionar por lo menos un producto.");
            return "user/formularioPedidos";
        }

        pedido.setUsuario(userDetails.getUsuario());

        for(Pedido p : pedidos){
            if(p.equals(pedido)){
                pedido.setUsuario(p.getUsuario());
            }
        }

        return "redirect:/user/pedidos";
        
    }

    /* ADMIN */
    
    /**
     * Método de controlador encargado de mostrar la vista de pedidos de administrador
     * @param modelo
     * @return la plantilla de administrador especificada
     */
    @GetMapping("/admin/pedidos")
    public String viewPedidosAdminPage(Model modelo){
        return listByPageAdmin(modelo, 1, "estado", "asc");
    }

    @GetMapping("/admin/pedidos/page/{pageNumber}")
    public String listByPageAdmin(Model modelo, @PathVariable("pageNumber") int paginaActual,
            @Param("sortField") String sortField,
            @Param("sortDir") String sortDir){

        Page<Pedido> page = pedidoServicio.listByPage(paginaActual, sortField, sortDir);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Pedido> listaPedidos = page.getContent();

        modelo.addAttribute("paginaActual", paginaActual);
        modelo.addAttribute("totalItems", totalItems);
        modelo.addAttribute("totalPages", totalPages);
        modelo.addAttribute("listaPedidos", listaPedidos);
        modelo.addAttribute("sortField", sortField);
        modelo.addAttribute("sortDir", sortDir);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        modelo.addAttribute("reverseSortDir", reverseSortDir);

        return "admin/pedidosAdmin";

    }

    @GetMapping("/admin/pedidos/editar/{id}")
    public ModelAndView viewEditarReservaAdminPage(@PathVariable(name ="id") Integer id){
        ModelAndView modelo = new ModelAndView("admin/editarPedido");
        modelo.addObject("pedido", pedidoServicio.getById(id));

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

        modelo.addObject("listaComida", listaComida);
        modelo.addObject("listaBebida", listaBebida);

        return modelo;
    }

    @PostMapping("/admin/pedidos/agregar")
    public String savePedidoAdmin(@ModelAttribute("pedido") Pedido pedido, @AuthenticationPrincipal CustomUserDetails userDetails, Model modelo){


        List<Pedido> pedidosActivos = pedidoServicio.listAll();

        for(Pedido request : pedidosActivos){

            if(pedido.getId() == request.getId()){

                pedido.setFecha_pedido(request.getFecha_pedido());
                break;

            }
    
        }

        pedido.setUsuario(userDetails.getUsuario());
        pedidoServicio.guardar(pedido);

        return "redirect:/admin/pedidos";

    } 
    
    /**
     * Método encargado de eliminar por id las pedidos
     * @param id
     * @return 
     */
    @GetMapping("/admin/pedidos/eliminar/{id}")
    public String eliminarPedido(@PathVariable(name ="id") Integer id){
        pedidoServicio.delete(id);
        return "redirect:/admin/pedidosAdmin";
    }

    
  
}
