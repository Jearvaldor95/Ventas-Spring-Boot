package com.example.demo.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Modelo.Productos;
import com.example.demo.Repository.ProductosRepository;
import com.example.demo.Services.ProductosServices;

@Service
public class ProductosServicesImpl implements ProductosServices {
	
	@Autowired
	private ProductosRepository ProductoRepository;
/*
	@Override
	public List<Productos> getAll() {
		// TODO Auto-generated method stub
		return ProductoRepository.findAll();
	}

	@Override
	public Productos getEmpleado(int id) {
		// TODO Auto-generated method stub
		return ProductoRepository.findByIdProducto(id);
	}*/

	@Override
	public List<Productos> listar() {
		// TODO Auto-generated method stub
		return (List<Productos>)ProductoRepository.findAll();
	}

	@Override
	public Optional<Productos> listarId(int id) {
		// TODO Auto-generated method stub
		return ProductoRepository.findById(id);
	}

	@Override
	public Productos save(Productos p) {
		// TODO Auto-generated method stub
		//p.setFechaCreacion(new Date());
		return ProductoRepository.save(p);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		ProductoRepository.deleteById(id);
	}


}
