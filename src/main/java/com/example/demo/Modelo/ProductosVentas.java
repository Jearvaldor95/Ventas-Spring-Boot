package com.example.demo.Modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "productos_ventas")
public class ProductosVentas {
	@Id
	@Column(name="id_pv")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  int id;
	private int precio;
	private int cantidad;
	private int total;
	/*@EmbeddedId
	private ProductosVentasPK ID;*/
	
    @ManyToOne
    @JoinColumn(name = "idventa",nullable= false,insertable=true, updatable=true)
    private Ventas venta;
    
    @ManyToOne
    @JoinColumn(name = "idproducto", nullable= false,insertable=true, updatable=true)
    private Productos productos;
    
	public ProductosVentas(int precio, int cantidad,int total,Productos productos,Ventas venta) {
		super();
		this.precio = precio;
		this.cantidad = cantidad;
		this.total = total;
		this.productos = productos;
		this.venta= venta;
	}
	
	
	 public ProductosVentas() {
		super();
	}
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}

	public int getTotal() {
	        return this.cantidad * this.precio;
	    }


	public void setTotal(int total) {
		this.total = total;
	}


	public int getPrecio() {
		return precio;
	}


	public void setPrecio(int precio) {
		this.precio = precio;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	

	public Ventas getVenta() {
		return venta;
	}


	public void setVenta(Ventas venta) {
		this.venta = venta;
	}
	
	public Productos getProductos() {
		return productos;
	}
	
	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	
}
