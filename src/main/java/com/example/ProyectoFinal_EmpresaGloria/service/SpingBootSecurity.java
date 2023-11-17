package com.example.ProyectoFinal_EmpresaGloria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpingBootSecurity extends WebSecurityConfigurerAdapter {  //Hacemos uso del 
    //WebSecurityConfigurerAdapter el cual nos ayudara para la configuracion de roles en el sistema

    @Autowired //Nos permite la inyeccion de dependencias
    private UserDetailsService userDetailService;

    @Override //Asignacion de los metodos de la propia extension de WebSecurityConfigurerAdapter
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Encriptacion de la contraseña
        auth.userDetailsService(userDetailService).passwordEncoder(getEnecoder());
    }

    @Override //Asignacion de los metodos de la propia extension de WebSecurityConfigurerAdapter
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/administrador/**").hasRole("ADMIN") //cada direccion mapeada 
                //administrador sera para el rol de admin 
                //productos sera para el rol de admin 
                .antMatchers("/productos/**").hasRole("ADMIN")
                //y ambos podran acceder a lo que viene a ser el login del usuario para acceder
                //a la pagina
                .and().formLogin().loginPage("/usuario/login")
                .permitAll().defaultSuccessUrl("/usuario/acceder");
    }

    @Bean //Encriptacion de datos
    //Metodo BCryptPasswordEncoder el cual nos permitira encriptar la contraseña del usuario
    public BCryptPasswordEncoder getEnecoder() {
        return new BCryptPasswordEncoder(); //retornamos la encriptacion
    }

}
