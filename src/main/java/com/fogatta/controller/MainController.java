package com.fogatta.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fogatta.model.Usuario;
import com.fogatta.service.UsuarioServicio;
import com.fogatta.utility.Utility;


@Controller
public class MainController {

    @Autowired
    private UsuarioServicio servicio;

    @Autowired
    ProductoController product_controller;
    
    /**
     * Método encargado de mostrar la página index
     * @return la plantilla index de la aplicación
     */
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }


    /**
     * Método encargado de mostrar el formulario de registro para un cliente
     * @param model el cual usamos para pasar un objeto usuario que representa la persona a registrar
     * @return la plantilla que contiene el formulario de registro
     */
    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new Usuario());
        return "registroCliente";
    }


    /**
     * Método de controlador encargado de procesar el registro de un usuario
     * @param user
     * @param request
     * @return La plantilla indicativa de un proceso de registro exitoso
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    @PostMapping("/process_register")
    public String processRegistration(Usuario user,  HttpServletRequest request) throws UnsupportedEncodingException, MessagingException{
        
        servicio.register(user, Utility.getSiteURL(request));
        return "register/register_succes";

    }
    

    /**
     * Método de controlador encargado de verificar el registro de un usuario
     * @param code correspondiente a un token dado 
     * @return la plantilla de verificación, ya sea de proceso exitoso o fallido segun sea el caso
     */
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        boolean verified = servicio.verify(code);
        return "register/" + (verified ? "verify_success" : "verify_fail");
    }


    /**
     * Método de controlador encargado de mostrar el login de administrador
     * @return la plantilla que contiene el formulario de login administrador
     */
    @GetMapping("/admin/login")
    public String viewAdminLoginPage(){
        return "admin/loginAdmin";
    }

    
    /**
     * Método de controlador encargado de mostrar el login de usuario
     * @return la plantilla que contiene el formulario de login de usuario
     */
    @GetMapping("/user/login")
    public String viewUserLoginPage(Model modelo){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "user/loginCliente";
        }
 
        return product_controller.listByPageUser(modelo, 1, "tipo", "asc");

    }

    
    /* Mapeos de usuario */

    /**
     * Método encargado de mostrar la vista de reseñas de usuario
     * @return la plantilla de usuario especificada
     */
    @GetMapping("/user/resenias")
    public String viewReseniasUserPage(){
        return "user/reseniasCliente";
    }
    

    /* Mapeos de administrador */

    
    
    
    
    /**
     * Método de controlador encargado de mostrar la vista de reseñas de administrador
     * @return la plantilla de administrador especificada
     */
    @GetMapping("/admin/resenias")
    public String viewReseñasAdminPage(){
        return "admin/reseniasAdmin";
    }
    
    
    
}
