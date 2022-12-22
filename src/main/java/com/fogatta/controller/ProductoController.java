package com.fogatta.controller;

import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fogatta.model.Producto;
import com.fogatta.service.ProductoServicio;
import com.fogatta.utility.FileUploadUtil;

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
        return listByPageUser(modelo, 1, "tipo", "asc");
    }


    @GetMapping("/user/producto/page/{pageNumber}")
    public String listByPageUser(Model modelo, @PathVariable("pageNumber") int paginaActual,
            @Param("sortField") String sortField,
            @Param("sortDir") String sortDir){

        Page<Producto> page = servicio.listAll(paginaActual, sortField, sortDir);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Producto> listProducts = page.getContent();

        modelo.addAttribute("paginaActual", paginaActual);
        modelo.addAttribute("totalItems", totalItems);
        modelo.addAttribute("totalPages", totalPages);
        modelo.addAttribute("listProducts", listProducts);
        modelo.addAttribute("sortField", sortField);
        modelo.addAttribute("sortDir", sortDir);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        modelo.addAttribute("reverseSortDir", reverseSortDir);

        return "user/cartaCliente";

    }

    @GetMapping("/user/producto/detalles/{id}")
    public String verProducto(@PathVariable("id") Integer id, Model model){
        Producto producto = servicio.get(id);
        model.addAttribute("producto", producto);
        return "user/producto_detalles";
    }

    
    /**
     * Método de controlador encargado de mostrar la pagina de inicio de administrador
     * @return la plantilla del inicio de administrador
     */
    @GetMapping("/admin/home")
    public String viewAdminHomePage(Model modelo){
        return listByPageAdmin(modelo, 1, "id", "asc");
    }

    @GetMapping("/admin/producto/page/{pageNumber}")
    public String listByPageAdmin(Model modelo, @PathVariable("pageNumber") int paginaActual,
            @Param("sortField") String sortField,
            @Param("sortDir") String sortDir){

        Page<Producto> page = servicio.listAll(paginaActual, sortField, sortDir);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Producto> listProducts = page.getContent();

        modelo.addAttribute("paginaActual", paginaActual);
        modelo.addAttribute("totalItems", totalItems);
        modelo.addAttribute("totalPages", totalPages);
        modelo.addAttribute("listProducts", listProducts);
        modelo.addAttribute("sortField", sortField);
        modelo.addAttribute("sortDir", sortDir);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        modelo.addAttribute("reverseSortDir", reverseSortDir);

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
