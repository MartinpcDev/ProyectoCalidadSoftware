package com.example.ProyectoFinal_EmpresaGloria.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data  //Metodos get and set de la clase Usuario
@Entity //Es la vinculacion hacia la base de datos
@Table(name = "usuarios") //Creamos la tabla usuarios dentro de la base de datos ecommercegloria
public class Usuario { //Creamos la clase Usuario

    @Id // es un identificador de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el id automaticamente
    //atributos que contendra la tabla Usuario
    private Integer id;
    private String nombre;
    private String username;
    private String email;
    private String direccion;
    private String telefono;
    private String tipo;
    private String password;

    //Relacion hacia otra tabla
    @OneToMany(mappedBy = "usuario") //Relacion de uno a muchos
    private List<Producto> productos; //Creamos una lista de la clase Producto con la instancia producto 
    //que estara relacionado a la tabla usuario

    @OneToMany(mappedBy = "usuario") //Relacion de uno a muchos
    private List<Orden> ordenes; //Creamos una lista de la clase Orden con la instancia ordenes 
    //que estara relacionado a la tabla usuario

    @OneToMany(mappedBy = "usuario") //Relacion de uno a muchos
    private List<Contacto> contactos; //Creamos una lista de la clase Contacto con la instancia contactos 
    //que estara relacionado a la tabla usuario

    //Constrcutor por defecto de la clase Usuario
    public Usuario() {
    }

    //Constructor con todos los parametros de la clase Usuario
    public Usuario(Integer id, String nombre, String username, String email, String direccion, String telefono,
            String tipo, String password) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.password = password;
    }

    //Metodo toString el cual retornara la informacion de la tabla Usuario
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", username=" + username + ", email=" + email
                + ", direccion=" + direccion + ", telefono=" + telefono + ", tipo=" + tipo + ", password=" + password
                + "]";
    }

}
