package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Modelo.Categorias;
import com.example.demo.Modelo.Productos;
import com.example.demo.Services.CategoriasServices;
import com.example.demo.Services.ProductosServices;

@Controller
@RequestMapping("/producto")
public class ProductosController {
	
	@Autowired
	private ProductosServices productoServices;
	@Autowired
	private CategoriasServices categoriaServices;
	
	@GetMapping("/listar")
	public String ListarProducto(Model model) {
		List<Productos>productos=productoServices.listar();
		model.addAttribute("productos", productos);
		return "index";
	}
	
	@GetMapping("/nuevo")
	public String MostrarFormularioAgregar(Model model) {
		model.addAttribute("productos",new Productos());
		//listar las categorias para seleccionar en el formulario 
		List<Categorias>ListarCategoria=categoriaServices.Listar();
		model.addAttribute("categoria", ListarCategoria);
		return "agregar_productos";
	}
	@PostMapping("/guardar")
	public String GuardarProducto(@Validated Productos p, Model model) {
		productoServices.save(p);
		return "agregar_productos";
	}
	
	//para pasar los datos al formulario de actualizar
	@GetMapping("/editar/{id}")
	public String MostrarFormularioEditar(@PathVariable int id, Model model) {
		model.addAttribute("productos", productoServices.listarId(id).orElse(null));
		//listar las categorias para seleccionar en el formulario 
				List<Categorias>ListarCategoria=categoriaServices.Listar();
				model.addAttribute("categoria", ListarCategoria);
		return "editar_producto";
	}
	
	@PostMapping("/editar/{id}")
	public String ActualizarProducto(@Validated Productos p) {
		productoServices.save(p);
		return "redirect:/producto/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String EliminarProducto(@PathVariable int id, Model model) {
		productoServices.delete(id);
		return "redirect:/producto/listar";
	}

}
