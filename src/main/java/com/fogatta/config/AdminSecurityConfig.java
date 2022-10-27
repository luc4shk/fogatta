package com.fogatta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.fogatta.details.CustomUserDetailsService;

@Configuration
@Order(1)
public class AdminSecurityConfig {
    
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
    
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception{

        // Permite que cualquier usuario vea la pagina de inicio
        http.authorizeRequests().antMatchers("/").permitAll();

        // todas las paginas que inicien con /admin solo pueden ser vistas por usuarios admin logeados
        http.antMatcher("/admin/**")
            .authorizeRequests().anyRequest().hasAnyAuthority("ADMIN")
            .and()
            .formLogin()
                .loginPage("/admin/login")
                .usernameParameter("email")
                .loginProcessingUrl("/admin/login")
                .defaultSuccessUrl("/admin/home")
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/");
            
        http.authenticationProvider(authenticationProvider());
        
        return http.build();

    }

}
