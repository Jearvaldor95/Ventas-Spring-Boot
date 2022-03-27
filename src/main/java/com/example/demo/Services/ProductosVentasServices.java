package com.example.demo.Services;

import java.util.List;

import com.example.demo.Modelo.ProductosVentas;

public interface ProductosVentasServices {
	
	public List<ProductosVentas> listarPV();
	
	ProductosVentas save(ProductosVentas pv);

}
