/*package com.fogatta.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fogatta.model.Horario;
//import com.fogatta.model.Mesa;
import com.fogatta.repositorios.HorarioRepositorio;
//import com.fogatta.repositorios.MesaRepositorio;

@Configuration
public class DarabaseLoader {

    @Autowired
    private MesaRepositorio mesaRepo;

    @Autowired
    private HorarioRepositorio horarioRepo;

    @Bean
    public CommandLineRunner initializeDatabase(){
        return args -> {
            
            for(int i = 1; i <= 10; i++){
                Mesa table = new Mesa();
                table.setNombre("Mesa " + i);
                table.setMax_ocupantes(4);
                mesaRepo.save(table);
            } 

            Horario horario1 = new Horario("12:00 AM");
            Horario horario2 = new Horario("2:00 PM");
            Horario horario3 = new Horario("4:00 PM");
            Horario horario4 = new Horario("6:00 PM");
            Horario horario5 = new Horario("8:00 PM");
            Horario horario6 = new Horario("10:00 PM");

            horarioRepo.save(horario1);
            horarioRepo.save(horario2);
            horarioRepo.save(horario3);
            horarioRepo.save(horario4);
            horarioRepo.save(horario5);
            horarioRepo.save(horario6);

            System.out.println("BENZEMAAAAAAAAAAAAAAA");

        };
    }
    
}*/
