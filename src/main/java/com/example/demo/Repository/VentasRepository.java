package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Modelo.Ventas;

public interface VentasRepository extends JpaRepository<Ventas, Integer> {

}
