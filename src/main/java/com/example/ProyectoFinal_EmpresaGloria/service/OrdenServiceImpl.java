package com.example.ProyectoFinal_EmpresaGloria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoFinal_EmpresaGloria.model.Orden;
import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;
import com.example.ProyectoFinal_EmpresaGloria.repository.IOrdenRepository;

@Service
public class OrdenServiceImpl implements IOrdenService { //Implementamos los metodos del 
    //IOrdenService

    @Autowired //Nos permite la inyeccion de dependencias
    private IOrdenRepository ordenRepository;

    @Override //Asignacion de los metodos de la propia implemetancion de ordenRepository
    //Implementacion del metodo save para guardar la informacion de la orden
    public Orden save(Orden orden) {
        return ordenRepository.save(orden); //Llamamos al objeto 
        //detalleOrdenRepository y le asignamos el save que contendra el objeto orden
        //de la clase Orden el cual nos ayudara a guardar la informacion de la orden
    }

    @Override //Asignacion de los metodos de la propia implemetancion de ordenRepository
    //Implementacion del metodo findAll para listar la informacion de la base de datos
    public List<Orden> findAll() {
        return ordenRepository.findAll(); //Llamamos al objeto 
        //ordenRepository y le asignamos el findAll que nos ayudara a listar todos los
        //datos de la base de datos
    }

    //Metodo el cual nos ayudara a generar los numero de orden de cada compra
    public String generarNumeroOrden() {
        int numero = 0; //inicializamos la variable numero en 0
        String numeroConcatenado = ""; //Declaramos la variable numeroConcatenado de tipo cadena
        //el cual nos ayudara a concatenar los numeros de orden con el numero generado y los ceros

        List<Orden> ordenes = findAll(); //Creamos una lista de la clase Orden con el objeto ordenes
        //el cual nos ayudara a listar toda la informacion de la base de datos

        List<Integer> numeros = new ArrayList<Integer>(); //arrayList numeros el cual se ira agregando
        //al campo numero de la orden

        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero()))); //hacemos el 
        //almacenamiento de la coleccion de datos en el objeto ordenes y le agregamos el numero de orden

        if (ordenes.isEmpty()) { //Comprobamos si el objeto ordenes se encuentra vacio
            numero = 1; //si esta vacio el numero de orden comenzara con el numero 1
        } else {
            numero = numeros.stream().max(Integer::compare).get(); //si no esta vacio, se iran comporando
            //los datos 
            numero++; //de acuerdo a la anterior linea de codigo la orden se ira incrementando en una 
            //unidad
        }

        if (numero < 10) { //si el numero de orden es mayor a 10
            numeroConcatenado = "000" + String.valueOf(numero); //la concatenacion sera la siguiente
        } else if (numero < 100) { //si el numero de orden es mayor a 100 
            numeroConcatenado = "000" + String.valueOf(numero); //la concatenacion sera la siguiente
        } else if (numero < 1000) { //si el numero de orden es mayor a 1000
            numeroConcatenado = "000" + String.valueOf(numero); //la concatenacion sera la siguiente
        } else if (numero < 10000) { //si el numero de orden es mayor a 10 000
            numeroConcatenado = "000" + String.valueOf(numero); //la concatenacion sera la siguiente
        }

        return numeroConcatenado; // de acuerdo a cada condicion iremos retornando el numero de orden
        //de la compra
    }

    @Override //Asignacion de los metodos de la propia implemetancion de ordenRepository
    public List<Orden> findByUsuario(Usuario usuario) {
        //Implementacion del metodo findByUsuario para listar la informacion del usuario
        return ordenRepository.findByUsuario(usuario); //Llamamos al objeto 
        //ordenRepository y le asignamos el findByUsuario que nos ayudara a listar el usuario
        //que realizo la orden
    }

    @Override //Asignacion de los metodos de la propia implemetancion de ordenRepository
    //Implementacion del metodo findById para listar la informacion por el id de la orden
    public Optional<Orden> findById(Integer id) {
        return ordenRepository.findById(id); //Llamamos al objeto 
        //ordenRepository y le asignamos el findById que nos ayudara a listar la orden
        //a tarves de su id
    }

    @Override //Asignacion de los metodos de la propia implemetancion de ordenRepository
    //Implementacion del metodo listarOrdenBuscar para realizar el filtrado de busqueda
    public List<Orden> listarOrdenBuscar(String numero) {
        return ordenRepository.findOrdenBuscar(numero); //Llamamos al objeto 
        //ordenRepository y le asignamos el findOrdenBuscar el cual nos ayudara a realizar
        //la busqueda de informacion a traves del numero de la orden
    }

}
