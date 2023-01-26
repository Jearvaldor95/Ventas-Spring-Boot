package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import com.example.demo.Modelo.Categorias;

public interface CategoriasServices {
	//List<Categorias> getAll();
    //Categorias getCategoria(int id);
	public List<Categorias>Listar();
	public Optional<Categorias>ListaId(int id);
	public int save(Categorias id);
	void deleted(int id);

}
