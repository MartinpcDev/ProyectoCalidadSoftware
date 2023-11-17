package com.example.ProyectoFinal_EmpresaGloria.service;

import com.example.ProyectoFinal_EmpresaGloria.model.Contacto;
import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;
import com.example.ProyectoFinal_EmpresaGloria.repository.IContactoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoServicelmpl implements IContactoService{ //Implementamos los metodos del 
    //IContactoService

    @Autowired //Nos permite la inyeccion de dependencias
	private IContactoRepository contactoRepository; //Declamos un objeto de IContactoRepository que se
    //llamara contactoRepository
    
    @Override
    //Implementacion del metodo findAll para listar toda la informacion de la base de datos
    public List<Contacto> findAll() { 
        return contactoRepository.findAll(); //Llamamos al objeto contactoRepository y le asignamos
        //el findAll que contendra toda la informacion de la base de datos
    }

    @Override //Asignacion de los metodos de la propia implemetancion de IContactoService
    public Optional<Contacto> findById(Integer id) {
        return contactoRepository.findById(id); //Llamamos al objeto contactoRepository y le asignamos
        //el findById que contendra el id del contacto a traves del parametro id propia del metodo
    }

    @Override //Asignacion de los metodos de la propia implemetancion de IContactoService
    public List<Contacto> findByUsuario(Usuario usuario) {
        return contactoRepository.findByUsuario(usuario); //Llamamos al objeto contactoRepository 
        //y le asignamos el findByUsuario que contendra el objeto de la clase Usuario que nos ayudara
        //a listar la informacion solo del usuario
    }

    @Override //Asignacion de los metodos de la propia implemetancion de IContactoService
    public Contacto save(Contacto contacto) {
        return contactoRepository.save(contacto); //Llamamos al objeto contactoRepository 
        //y le asignamos el save que contendra el objeto de la clase Usuario que nos ayudara
        //a guardar la informacion del contacto registrado
    }

    @Override //Asignacion de los metodos de la propia implemetancion de IContactoService
    public List<Contacto> listarContactoBuscar(String email) {
        return contactoRepository.findContactoBuscar(email); //Llamamos al objeto contactoRepository 
        //y le asignamos el findContactoBuscar que contendra el parametro de tipo cadena email
        //el cual nos ayudara a realizar el filtrado de busqueda de informacion
    }
    
}
