package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import com.example.demo.Modelo.Ventas;

public interface VentasServices {
	public List<Ventas>listar();
	public Optional<Ventas>listarId(int id);
	public int save(Ventas v);
	public void delete(int id);

}
