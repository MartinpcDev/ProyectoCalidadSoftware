package com.example.ProyectoFinal_EmpresaGloria.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;

@Service
public class UserDetailServiceImpl implements UserDetailsService { //Implementamos los metodos del 
    //UserDetailsService

    @Autowired //Nos permite la inyeccion de dependencias
    private IUsuarioService usuarioService;

    @Autowired //Nos permite la inyeccion de dependencias
    private BCryptPasswordEncoder bCrypt; //Clase BCryptPasswordEncoder con la instancia bCrypt que 
    //nos ayudara en la encriptacion de la contraseña

    @Autowired //Nos permite la inyeccion de dependencias
    HttpSession session; //Clase HttpSession la instanciamos con session para usarlo en la
    //sesion del usuario

    private Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class); //el Logger nos permitira
    //mostrar los datos de la sesion a traves de la consola

    @Override //Asignamos la creacion del siguiente metodo a traves de UserDetailsService
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Este es el username val, {}", username); //mostramos el usuario logeado en consola
        session.setAttribute("username",username);
        Optional<Usuario> optionalUser = usuarioService.findByEmail(username); //A traves del optional
        //listamos el email del usuario registrado
        if (optionalUser.isPresent()) { //Verificamos si el usuario logeado se encuentra presente
            log.info("Esto es el id del usuario: {}", optionalUser.get().getId()); //Mostramos el id
            //del usuario logeado
            session.setAttribute("idusuario", optionalUser.get().getId()); //lo agregamos a la session
            Usuario usuario = optionalUser.get(); //Instanciamos el objeto usuario de la clase Usuario
            //el cual nos ayudara a retornar los datos de la session
            return User.builder().username(usuario.getNombre()).password(usuario.getPassword()).roles(usuario.getTipo()).build();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado"); //si el usuario no se encuentra
            //mostrara el siguiente mensaje
        }
    }

}
