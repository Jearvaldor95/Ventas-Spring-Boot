package com.example.demo.Modelo;


public class ProductoParaVender extends Productos {
	 private int cantidad;


	    public ProductoParaVender(int id,String nombre, int precio_compra, int precio_venta, int stock, int cantidad) {
	        super(id,nombre, precio_compra,precio_venta, stock);
	        this.cantidad = cantidad;
	    }

	    public void aumentarCantidad() {
	        this.cantidad++;
	    }
	    
	 

	    public int getCantidad() {
	        return cantidad;
	    }

	    public int getTotal() {
	        return this.getPrecio_venta() * this.cantidad;
	    }

}

