package com.example.demo.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Modelo.ProductosVentas;
import com.example.demo.Repository.ProductosVentasRepository;
import com.example.demo.Services.ProductosVentasServices;

@Service
public class ProductosVentasServicesImpl implements ProductosVentasServices {

	@Autowired
	private ProductosVentasRepository productosVentasRepository;
	
	@Override
	public ProductosVentas save(ProductosVentas pv) {
		// TODO Auto-generated method stub
		return productosVentasRepository.save(pv);
	}

	@Override
	public List<ProductosVentas> listarPV() {
		// TODO Auto-generated method stub
		return (List<ProductosVentas>)productosVentasRepository.findAll();
	}

}
