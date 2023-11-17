package com.example.ProyectoFinal_EmpresaGloria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoFinal_EmpresaGloria.model.DetalleOrden;
import com.example.ProyectoFinal_EmpresaGloria.repository.IDetalleOrdenRepository;
import java.util.List;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService{ //Implementamos los metodos del 
    //IDetalleOrdenService
	
	@Autowired //Nos permite la inyeccion de dependencias
	private IDetalleOrdenRepository detalleOrdenRepository;

	@Override //Asignacion de los metodos de la propia implemetancion de IDetalleOrdenService
        //Implementacion del metodo save para guardar la informacion del detalle de la orden
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden); //Llamamos al objeto 
                //detalleOrdenRepository y le asignamos el save que contendra el objeto detalleOrden
                //de la clase DetalleOrden el cual nos ayudara a listar los detalles de cada orden
	}

    @Override
    public List<DetalleOrden> findAll() {
        return detalleOrdenRepository.findAll();
    }

    @Override
    public List<DetalleOrden> CantidadProductos(int id) {
        return detalleOrdenRepository.CantidadProductos(id);
    }

}
