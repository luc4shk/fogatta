package com.fogatta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2) // Puesto que tenemos 2 cadenas de filtro de seguridad compatibles, especificamos un orden diferente
public class UserSecurityConfig {

    // Debido a que ambos modulos de configuración comparten el mismo servicio de
    // detalle de usuario y codificador de contraseña no son necesesarios especificarlos
    // aqui

    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception{

        http.authorizeRequests().antMatchers("/").permitAll();

        http.antMatcher("/user/**")
            .authorizeRequests().anyRequest().hasAuthority("USER")
            .and()
            .formLogin()
                .loginPage("/user/login")
                .usernameParameter("email")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/user/home")
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/");

        return http.build();
    }
    
}
