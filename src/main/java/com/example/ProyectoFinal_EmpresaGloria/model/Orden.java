package com.example.ProyectoFinal_EmpresaGloria.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data //Metodos get and set de la clase Orden
@Entity //Es la vinculacion hacia la base de datos
@Table(name = "ordenes") //Creamos la tabla ordenes dentro de la base de datos ecommercegloria
public class Orden { //Creamos la clase Orden

    @Id // es un identificador de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el id automaticamente
    //atributos que contendra la tabla Orden
    private Integer id;
    private String numero;
    private Date fechaCreacion;
    private Date fechaRecibida;
    private double total;

    //Relacion hacia otra tabla
    @ManyToOne //Relacion de muchos a uno
    private Usuario usuario; //Declaramos una instancia usuario de la clase Usuario

    @OneToMany(mappedBy = "orden") //Apuntamos a la entidad propietaria de la relacion
    private List<DetalleOrden> detalle; //Declaramos un lista de la clase DetalleOrden y la 
    //instanciamos con la variable orden

    //Constrcutor por defecto de la clase Orden
    public Orden() {

    }

    //Constructor con todos los parametros de la clase Orden
    public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, double total) {
        super();
        this.id = id;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.fechaRecibida = fechaRecibida;
        this.total = total;
    }

    //Metodo toString el cual retornara la informacion de la tabla Orden
    @Override
    public String toString() {
        return "Orden [id=" + id + ", numero=" + numero + ", fechaCreacion=" + fechaCreacion + ", fechaRecibida="
                + fechaRecibida + ", total=" + total + "]";
    }

}
