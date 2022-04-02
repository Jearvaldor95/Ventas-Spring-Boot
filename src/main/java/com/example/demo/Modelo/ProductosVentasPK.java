package com.example.demo.Modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductosVentasPK implements Serializable {
	private int idventa;
	private int idproducto;
	public int getIdventa() {
		return idventa;
	}
	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	
	

}
