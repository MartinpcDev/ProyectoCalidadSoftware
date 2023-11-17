package com.example.ProyectoFinal_EmpresaGloria.service;

import java.util.List;
import java.util.Optional;

import com.example.ProyectoFinal_EmpresaGloria.model.Contacto;
import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;

public interface IContactoService {

    List<Contacto> findAll(); //Lista contacto findAll el cual nos ayuda a listar todos los datos
    //directamente de la base de datos
    public Contacto save( Contacto contacto); //Instanciamos el metodo save de la Clase Contacto el cual
    //nos ayudara a guardar los registros de los contactos que se iran registrando
    Optional<Contacto> findById(Integer id); //Listamos el id directamente de la base de datos
    List<Contacto> findByUsuario(Usuario usuario); //Lista Usuario el cual nos ayudara a listar la 
    //informacion
    public List<Contacto> listarContactoBuscar(String email); //Metodo listarContactoBuscar el cual
    //nos ayudara para el filtrado de la informacion a la hora de hacer la busqueda de datos
}
