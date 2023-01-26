package com.example.demo.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Modelo.Categorias;
import com.example.demo.Modelo.Ventas;
import com.example.demo.Repository.CategoriasRepository;
import com.example.demo.Services.CategoriasServices;

@Service
public class CategoriasServicesImpl implements CategoriasServices {
	
	@Autowired
	private CategoriasRepository categoriasRepository;

	@Override
	public List<Categorias> Listar() {
		// TODO Auto-generated method stub
		return (List<Categorias>)categoriasRepository.findAll();
	}

	@Override
	public Optional<Categorias> ListaId(int id) {
		// TODO Auto-generated method stub
		return categoriasRepository.findById(id);
	}

	@Override
	public int save(Categorias id) {
		// TODO Auto-generated method stub
		int res=0;
		Categorias categoria= categoriasRepository.save(id);
		if(categoria.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void deleted(int id) {
		// TODO Auto-generated method stub
		categoriasRepository.deleteById(id);
	}

}
