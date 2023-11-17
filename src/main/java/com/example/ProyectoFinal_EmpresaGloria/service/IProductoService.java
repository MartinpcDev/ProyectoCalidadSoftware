package com.example.ProyectoFinal_EmpresaGloria.service;

import com.example.ProyectoFinal_EmpresaGloria.model.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    public Producto save(Producto producto); //Metodo que nos ayudara en el guardado de la informacion
    //del producto
    public Optional<Producto> get(Integer id); //Capturamos el id del producto desde la base de datos
    public void update(Producto producto); //Metodo que nos ayudara en la actualizacion de informacion
    //del producto
    public void delete(Integer id); //Metodo que nos ayudara en la eliminacion de informacion de un 
    //producto por su id
    public List<Producto> findAll(); //Listamos toda la informacion que contiene la tabla productos
    //directamente de la base de datos
    public List<Producto> listarProductoBuscar(String nombre); //Metodo que nos ayudara en el filtrado
    //de informacion por el nombre del producto
}
