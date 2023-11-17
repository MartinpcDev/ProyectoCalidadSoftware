package com.example.ProyectoFinal_EmpresaGloria.service;

import java.util.List;
import java.util.Optional;

import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;

public interface IUsuarioService {
	List<Usuario> findAll(); //Metodo que nos ayudara en el listado de la informacion de los usuario
        //directamente de la base de datos
	Optional<Usuario> findById(Integer id); //Hacemos la verificacion del id del usuario desde la 
        //base de datos con el parametro id del propio metodo
	Usuario save (Usuario usuario); //Guardamos la informacion del usuario
	Optional<Usuario> findByEmail(String email); //Verificamos el email del usuario de la base de datos
        //a traves del parametro del metodo de tipo cadena email
        public List<Usuario> listarUsuarioBuscar(String nombre); //Metodo que nos ayudara en el filtrado
        //de informacion a traves del nombre del usuario que deseamos buscar
        
        public List<Usuario> CantidadUsuarios(int id);
}
