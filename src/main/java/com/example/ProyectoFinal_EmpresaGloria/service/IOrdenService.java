package com.example.ProyectoFinal_EmpresaGloria.service;

import java.util.List;
import java.util.Optional;

import com.example.ProyectoFinal_EmpresaGloria.model.Orden;
import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;

public interface IOrdenService {

    List<Orden> findAll(); //Metodo que nos ayudara a Listar la informacion de la base de datos
    Optional<Orden> findById(Integer id); //Metodo con el parametro id que nos ayudara a verificar
    //el id de la orden en la base de datos
    Orden save(Orden orden); //Guardamos la informacion de la orden
    String generarNumeroOrden(); //Metodo que nos ayudara a generar el numero de orden
    List<Orden> findByUsuario(Usuario usuario); //Listamos la informacion del usuario que genero
    //la orden
    public List<Orden> listarOrdenBuscar(String numero); //Metodo que nos ayudara para el filtrado de 
    //informacion a la hora de hacer la busqueda por el numero de orden
}
