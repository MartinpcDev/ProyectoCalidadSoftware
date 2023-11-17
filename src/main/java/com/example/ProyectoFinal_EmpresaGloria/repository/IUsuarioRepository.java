package com.example.ProyectoFinal_EmpresaGloria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
	Optional<Usuario> findByEmail(String email); //Optional de tipo usuario el cual contendra
        //el email del usuario directamente de la base de datos
        //Query para hacer el filtrado de informacion del los usuarios registrados en el sistema
        @Query(value="SELECT * FROM `usuarios` where usuarios.nombre like %?1%",nativeQuery=true)
        //Lista Usuario el cual contendra el parametro nombre que nos ayudara en el filtrado de la
        //informacion a traves del nombre del usuario
        List<Usuario> findUsuarioBuscar(String nombre);
        
        @Query(value="select * from `usuarios` where usuarios.id = ?1",nativeQuery=true)
        List<Usuario> CantidadUsuarios(int id);
}
