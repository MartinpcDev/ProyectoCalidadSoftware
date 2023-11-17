package com.example.ProyectoFinal_EmpresaGloria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data //Metodos get and set de la clase Contacto
@Entity //Es la vinculacion hacia la base de datos
@Table(name = "contacto") //Creamos la tabla contacto dentro de la base de datos ecommercegloria
public class Contacto { //Creamos la clase Contacto

    @Id // es un identificador de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el id automaticamente
    //atributos que contendra la tabla contacto
    private Integer id;
    private String nombre;
    private String email;
    private String asunto;
    private String mensaje;

    //Relacion hacia otra tabla
    @ManyToOne //Relacion de muchos a uno
    //Muchas quejas o recomendaciones le corresponde a un usuario
    private Usuario usuario; //Declaramos una instancia usuario de la clase Usuario

    //Constrcutor por defecto de la clase Contacto
    public Contacto() {
    }

    //Constructor con todos los parametros de la clase Contacto
    public Contacto(Integer id, String nombre, String email, String asunto, String mensaje, Usuario usuario) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    //Metodo toString el cual retornara la informacion de la tabla Contacto
    @Override
    public String toString() {
        return "Contacto{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", asunto=" + asunto + ", mensaje=" + mensaje + '}';
    }

}
