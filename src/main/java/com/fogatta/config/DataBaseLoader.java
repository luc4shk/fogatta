//
//package com.fogatta.config;
//
//import com.fogatta.model.Rol;
//import com.fogatta.model.Usuario;
//import com.fogatta.repositorios.UsuarioRepositorio;
//import java.time.LocalDate;
//import java.time.Month;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// *
// * @author omars
// */
//@Configuration
//public class DataBaseLoader {
//    
//    @Autowired
//    private UsuarioRepositorio userRepo;
//    
//    @Bean
//    public CommandLineRunner initianizeDataBase(){
//        return args -> {
//            Usuario user = new Usuario("Sam","Boa","omarsamuel21@gmail.com","cualquieraahi","1234567890","Av 2",LocalDate.of(2001, 11, 1),Rol.ADMIN);  
//            user.setEnabled(true);
//            userRepo.save(user);
//            System.out.println("HOLA SÍ");
//        };
//    }
//}
