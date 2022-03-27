package com.example.demo.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Modelo.Ventas;
import com.example.demo.Repository.VentasRepository;
import com.example.demo.Services.VentasServices;

@Service
public class VentasServicesImpl implements VentasServices {
	@Autowired
	private VentasRepository ventasRepository;

	@Override
	public List<Ventas> listar() {
		// TODO Auto-generated method stub
		return  (List<Ventas>)ventasRepository.findAll();
	}

	@Override
	public Optional<Ventas> listarId(int id) {
		// TODO Auto-generated method stub
		return ventasRepository.findById(id);
	}

	@Override
	public int save(Ventas v) {
		// TODO Auto-generated method stub
		int res=0;
		Ventas venta=ventasRepository.save(v);
		if(venta.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		ventasRepository.deleteById(id);
	}

}
