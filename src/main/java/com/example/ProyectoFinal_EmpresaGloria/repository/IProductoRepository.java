package com.example.ProyectoFinal_EmpresaGloria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoFinal_EmpresaGloria.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
    //Query que nos ayudara en el flitrado de informacion de los productos 
    @Query(value="SELECT * FROM `productos` where productos.nombre like %?1%",nativeQuery=true)
    //Lista Producto con el parametro que se hara el filtrado de la informacion, a traves del nombre
    //del producto
    List<Producto> findProductoBuscar(String nombre);
}
