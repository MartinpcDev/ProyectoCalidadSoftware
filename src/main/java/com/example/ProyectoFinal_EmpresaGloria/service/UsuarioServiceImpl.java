package com.example.ProyectoFinal_EmpresaGloria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;
import com.example.ProyectoFinal_EmpresaGloria.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService { //Implementamos los metodos del 
    //IUsuarioService

    @Autowired //Nos permite la inyeccion de dependencias
    private IUsuarioRepository usuarioRepository;
 
    @Override //Asignacion de los metodos de la propia implemetancion de usuarioRepository
    //Implementacion del metodo findById el cual capturara el id del usuario de la base de datos
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override //Asignacion de los metodos de la propia implemetancion de usuarioRepository
    //Implementacion del metodo save el cual guardara la informacion 
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override //Asignacion de los metodos de la propia implemetancion de usuarioRepository
    //Implementacion del metodo findByEmail el cual capturara el email del usuario
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override //Asignacion de los metodos de la propia implemetancion de usuarioRepository
    //Implementacion del metodo findAll el cual listara toda la informacion de la base de datos
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override //Asignacion de los metodos de la propia implemetancion de usuarioRepository
    //Implementacion del metodo listarUsuarioBuscar el cual hara el filtrado de la informacion
    public List<Usuario> listarUsuarioBuscar(String nombre) {
        return usuarioRepository.findUsuarioBuscar(nombre);
    }

    @Override
    public List<Usuario> CantidadUsuarios(int id) {
        return usuarioRepository.CantidadUsuarios(id);
    }



}
