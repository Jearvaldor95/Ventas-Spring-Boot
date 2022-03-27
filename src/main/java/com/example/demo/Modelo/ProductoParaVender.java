package com.example.demo.Modelo;


public class ProductoParaVender extends Productos {
	 private int cantidad;


	    public ProductoParaVender(int id,String nombre, int precio, int stock, int cantidad) {
	        super(id,nombre, precio, stock);
	        this.cantidad = cantidad;
	    }

	    public void aumentarCantidad() {
	        this.cantidad++;
	    }
	    
	 

	    public int getCantidad() {
	        return cantidad;
	    }

	    public int getTotal() {
	        return this.getPrecio() * this.cantidad;
	    }

}

