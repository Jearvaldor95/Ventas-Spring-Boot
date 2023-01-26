package com.example.demo.Modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name ="productos")
public class Productos {

	@Id
	@Column(name="idproducto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Debes especificar el nombre")
	private String nombre;

	@NotNull(message = "Debes especificar el precio compra")
	@Min(value = 0, message = "El precio mínimo es 0")
	private int precio_compra;

	@NotNull(message = "Debes especificar el precio venta")
	@Min(value = 0, message = "El precio mínimo es 0")
	private int precio_venta;

	@NotNull(message = "Debes especificar el peso")
	@Min(value = 0, message = "El peso mínimo es 0")
	private int peso;

	@NotNull(message = "Debes seleccionar la categoria")
	private int idcategoria;

	@NotNull(message = "Debes especificar la existencia")
	@Min(value = 0, message = "La existencia mínima es 0")
	private int stock;

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria",nullable= false,insertable=false, updatable=false)
	private Categorias categorias;
	
	
	@OneToMany(mappedBy = "productos", cascade=CascadeType.ALL)
    private List<ProductosVentas> productosventas;
	

	public Productos(int id,String nombre, int precio_compra,int precio_venta, int stock) { 
		this.id = id;
		this.nombre = nombre;
		this.precio_compra = precio_compra;
		this.precio_venta = precio_venta;
		this.stock = stock;
	}
	
	public Productos(String nombre, int precio_compra, int precio_venta, int stock) {
		this.nombre=nombre;
		this.precio_compra=precio_compra;
		this.precio_venta=precio_venta;
		this.stock=stock;
		
	}
	 public Productos() {
	    }

	
	public boolean sinExistencia() {
        return this.stock <= 0;
    }
	public void restarExistencia(int stock) {
        this.stock -= stock;
    }
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getPrecio_compra() {
		return precio_compra;
	}

	public void setPrecio_compra(int precio_compra) {
		this.precio_compra = precio_compra;
	}

	public int getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(int precio_venta) {
		this.precio_venta = precio_venta;
	}

	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getCategoria() {
		return idcategoria;
	}
	public void setCategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public List<ProductosVentas> getProductosventas() {
		return productosventas;
	}

	public void setProductos(List<ProductosVentas> productosventas) {
		this.productosventas = productosventas;
	}

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

	
}
