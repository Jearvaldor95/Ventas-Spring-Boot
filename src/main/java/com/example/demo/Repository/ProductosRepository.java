package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelo.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {
	
	Productos findFirstById(Integer Id);

}
