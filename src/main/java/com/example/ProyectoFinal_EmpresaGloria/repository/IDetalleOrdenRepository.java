package com.example.ProyectoFinal_EmpresaGloria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoFinal_EmpresaGloria.model.DetalleOrden;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {
    @Query(value="select * from detalles inner join productos on detalles.id = productos.id where detalles.id = '?1'",nativeQuery=true)
        List<DetalleOrden> CantidadProductos(int id);   
}
