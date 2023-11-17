package com.example.ProyectoFinal_EmpresaGloria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data //Metodos get and set de la clase Producto
@Entity //Es la vinculacion hacia la base de datos
@Table(name = "productos") //Creamos la tabla productos dentro de la base de datos ecommercegloria
public class Producto { //Creamos la clase Producto

    @Id // es un identificador de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el id automaticamente
    //atributos que contendra la tabla productos
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private double precio;
    private int cantidad;

    //Relacion hacia otra tabla
    @ManyToOne //Relacion de muchos a uno
    //Muchos productos pueden ser creados por un usuario
    private Usuario usuario; //Declaramos una instancia usuario de la clase Usuario

    //Constrcutor por defecto de la clase Producto
    public Producto() {

    }

    //Constructor con todos los parametros de la clase Producto
    public Producto(Integer id, String nombre, String descripcion, String imagen, double precio, int cantidad,
            Usuario usuario) {
        super(); 
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.cantidad = cantidad;
        this.usuario = usuario;
    }
    
    //Metodo toString el cual retornara la informacion de la tabla Producto
    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen
                + ", precio=" + precio + ", cantidad=" + cantidad + "]";
    }

}
