package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Modelo.ProductosVentas;

public interface ProductosVentasRepository extends JpaRepository<ProductosVentas, Integer> {

}
