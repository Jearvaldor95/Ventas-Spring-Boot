package com.example.demo.Modelo;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
	@Size(min = 1, max = 50, message = "El nombre debe medir entre 1 y 50")
	private String nombre;

	@NotNull(message = "Debes especificar la referencia")
	@Size(min = 1, max = 100, message = "lareferencia debe medir entre 1 y 50")
	private String referencia;

	@NotNull(message = "Debes especificar el precio")
	@Min(value = 0, message = "El precio mínimo es 0")
	private int precio;

	@NotNull(message = "Debes especificar el peso")
	@Min(value = 0, message = "El precio mínimo es 0")
	private int peso;

	@NotNull(message = "Debes especificar la categoria")
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
    private Set<ProductosVentas> productosventas;

	public Productos(int id,String nombre, int precio, int stock) { 
		this.id = id;
		this.nombre = nombre;
		//this.referencia = referencia;
		this.precio = precio;
		//this.peso = peso;
		//this.categoria = categoria;
		this.stock = stock;
		//this.fechaCreacion = fechaCreacion;
	}/*
	public Productos(String nombre,String referencia, int precio, int peso, String categoria, int stock) { 
		this.nombre = nombre;
		this.referencia = referencia;
		this.precio = precio;
		this.peso = peso;
		this.categoria = categoria;
		this.stock = stock;
		//this.fechaCreacion = new Date();
	}*/
	
	public Productos(String nombre, int precio, int stock) {
		this.nombre=nombre;
		this.precio=precio;
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
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
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
	
	public Set<ProductosVentas> getProductosventas() {
		return productosventas;
	}

	public void setProductos(Set<ProductosVentas> productosventas) {
		this.productosventas = productosventas;
	}

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

	
}