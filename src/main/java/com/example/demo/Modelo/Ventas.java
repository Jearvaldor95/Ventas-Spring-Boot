package com.example.demo.Modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ventas")
public class Ventas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idventa;
	@Column(name ="fecha_venta")
	 private String fechaYHora;
	@Column(name ="totalventa")
	 private float TotalVenta;
	
	@OneToMany(mappedBy = "venta", cascade=CascadeType.ALL)
    private Set<ProductosVentas> productosventas;
	
	 public Ventas() {
	        this.fechaYHora = Utiles.obtenerFechaYHoraActual();
	      
	    }
	 
	 

	public int getIdventa() {
		return idventa;
	}

	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}

	public String getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(String fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public float getTotalVenta() {
	        
	        return TotalVenta;
	}

	public void setTotalVenta(float TotalVenta) {
		this.TotalVenta = TotalVenta;
	}

	public Set<ProductosVentas> getProductosventas() {
		return productosventas;
	}

	public void setProductosventas(Set<ProductosVentas> productosventas) {
		this.productosventas = productosventas;
	}
	
	

}
