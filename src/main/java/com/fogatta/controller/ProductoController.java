package com.fogatta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fogatta.model.Producto;
import com.fogatta.service.ProductoServicio;

@Controller
public class ProductoController {

    @Autowired
    private ProductoServicio servicio;

    /**
     * Método de controlador encargado de mostrar la pagina de inicio de usuario
     * @return la plantilla de inicio de usuario
     */
    @GetMapping("/user/home")
    public String viewUserHomePage(Model modelo){
        modelo.addAttribute("listaProductos2", servicio.listAll());
        return "user/cartaCliente";
    }

    /**
     * Método de controlador encargado de mostrar la pagina de inicio de administrador
     * @return la plantilla del inicio de administrador
     */
    @GetMapping("/admin/home")
    public String viewAdminHomePage(Model modelo){
        modelo.addAttribute("listaProductos", servicio.listAll());
        return "admin/productosAdmin";
    }

    /**
     * 
     * @return
     */
    @GetMapping("/admin/producto/nuevo")
    public String viewAgregarAdminPage(Model modelo){
        Producto producto = new Producto();
        modelo.addAttribute("producto", producto);
        return "admin/agregarProducto";
    }


    @PostMapping("/admin/producto/agregar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto){
        servicio.save(producto);
        return "redirect:/admin/home";
    }

    /**
     * 
     * @return
     */
    @GetMapping("/admin/producto/editar/{id}")
    public ModelAndView viewEditarProductoAdminPage(@PathVariable(name = "id") Integer id){
        ModelAndView modelo = new ModelAndView("admin/editarProducto");
        modelo.addObject("producto", servicio.get(id));
        return modelo;
    }


    @GetMapping("/admin/producto/eliminar/{id}")
    public String eliminarProducto(@PathVariable(name = "id") Integer id){
        servicio.delete(id);
        return "redirect:/admin/home";
    }
    
}
