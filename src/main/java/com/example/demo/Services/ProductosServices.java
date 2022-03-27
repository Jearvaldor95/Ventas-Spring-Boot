package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import com.example.demo.Modelo.Productos;

public interface ProductosServices {

	//List<Productos> getAll();
	//Productos getEmpleado(int id);
	public List<Productos>listar();
	public Optional<Productos>listarId(int id);
	Productos save(Productos id);
	void delete(int id);
}
