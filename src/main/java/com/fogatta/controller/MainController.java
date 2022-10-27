package com.fogatta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fogatta.service.UsuarioServicio;

@Controller
public class MainController {

    @Autowired
    private UsuarioServicio servicio;

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

}
