package com.example.ProyectoFinal_EmpresaGloria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data //Metodos get and set de la clase DetalleOrden
@Entity //Es la vinculacion hacia la base de datos
@Table(name = "detalles") //Creamos la tabla detalles dentro de la base de datos ecommercegloria
public class DetalleOrden { //Creamos la clase DetalleOrden

    @Id // es un identificador de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el id automaticamente
    //atributos que contendra la tabla DetalleOrden
    private Integer id;
    private String nombre;
    private double cantidad;
    private double precio;
    private double total;

    //Relacion hacia otra tabla
    @ManyToOne //Relacion de muchos a uno
    //Muchos detalles de orden le corresponde a una orden
    private Orden orden; //Declaramos una instancia orden de la clase Orden

    @ManyToOne //Relacion de muchos a uno
    //Muchos detalles de orden le corresponde a un producto
    private Producto producto; //Declaramos una instancia producto de la clase Producto

    //Constrcutor por defecto de la clase DetalleOrden
    public DetalleOrden() {

    }

    //Constructor con todos los parametros de la clase DetalleOrden
    public DetalleOrden(Integer id, String nombre, double cantidad, double precio, double total) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    //Metodo toString el cual retornara la informacion de la tabla DetalleOrden
    @Override
    public String toString() {
        return "DetalleOrden [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
                + ", total=" + total + "]";
    }

}
