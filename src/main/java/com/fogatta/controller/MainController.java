package com.fogatta.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new Usuario());
        return "registroCliente";
    }

    @PostMapping("/process_register")
    public String processRegistration(Usuario user,  HttpServletRequest request) throws UnsupportedEncodingException, MessagingException{
        
        servicio.register(user, Utility.getSiteURL(request));
        return "register/register_succes";

    }
    
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        boolean verified = servicio.verify(code);
        return "register/" + (verified ? "verify_success" : "verify_fail");
    }

    @GetMapping("/admin/login")
    public String viewAdminLoginPage(){
        return "admin/loginAdmin";
    }

    @GetMapping("/user/login")
    public String viewUserLoginPage(){
        return "user/loginCliente";
    }

    @GetMapping("/admin/home")
    public String viewAdminHomePage(){
        return "admin/productosAdmin";
    }

    @GetMapping("/user/home")
    public String viewUserHomePage(){
        return "user/cartaCliente";
    }


}
