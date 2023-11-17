package com.example.ProyectoFinal_EmpresaGloria.repository;

import java.util.List;


import com.example.ProyectoFinal_EmpresaGloria.model.Contacto;
import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactoRepository extends JpaRepository<Contacto, Integer> {

    List<Contacto> findByUsuario(Usuario usuario);
    //Query para hacer el filtrado de informacion en la busqueda de datos de los contactos
    @Query(value="SELECT * FROM `contacto` where contacto.email like %?1%",nativeQuery=true)
    //Creamos una lista Contacto que contendra el parametro que deseamos a buscar para el filtrado
    List<Contacto> findContactoBuscar(String email);
}
