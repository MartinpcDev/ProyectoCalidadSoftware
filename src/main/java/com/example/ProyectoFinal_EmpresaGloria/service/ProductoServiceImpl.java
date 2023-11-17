package com.example.ProyectoFinal_EmpresaGloria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoFinal_EmpresaGloria.model.Producto;
import com.example.ProyectoFinal_EmpresaGloria.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService { //Implementamos los metodos del 
    //IProductoService

    @Autowired //Nos permite la inyeccion de dependencias
    private IProductoRepository productoRepository;

    @Override //Asignacion de los metodos de la propia implemetancion de productoRepository
    //Implementacion del metodo save para guardar la informacion del producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override //Asignacion de los metodos de la propia implemetancion de productoRepository
    //Implementacion del metodo get listar la informacion de los productos por el id del mismo
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById(id);
    }

    @Override //Asignacion de los metodos de la propia implemetancion de productoRepository
    //Implementacion del metodo update el cual actualizara y guardara los datos del producto
    public void update(Producto producto) {
        productoRepository.save(producto);
    }

    @Override //Asignacion de los metodos de la propia implemetancion de productoRepository
    //Implementacion del metodo delete el cual eliminara el producto por el id
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override //Asignacion de los metodos de la propia implemetancion de productoRepository
    //Implementacion del metodo findAll el cual listara la informacion de la base de datos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override //Asignacion de los metodos de la propia implemetancion de productoRepository
    //Implementacion del metodo listarProductoBuscar el cual se encarga del filtrado de informacion
    public List<Producto> listarProductoBuscar(String nombre) {
        return productoRepository.findProductoBuscar(nombre);
    }

}
