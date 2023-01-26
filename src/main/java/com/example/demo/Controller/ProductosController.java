package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		return "Productos/ver_productos";
	}
	
	@GetMapping("/nuevo")
	public String MostrarFormularioAgregar(Model model) {
		model.addAttribute("productos",new Productos());
		//listar las categorias para seleccionar en el formulario 
		List<Categorias>ListarCategoria=categoriaServices.Listar();
		model.addAttribute("categoria", ListarCategoria);
		return "Productos/agregar_productos";
	}
	@PostMapping("/guardar")
	public String GuardarProducto(@Valid Productos p, Model model, RedirectAttributes redirectAtrrs,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            return "Productos/agregar_productos";
        }
		productoServices.save(p);
		redirectAtrrs
		.addFlashAttribute("mensaje", "Producto guardado corectamente")
		.addFlashAttribute("clase", "success");
		return "redirect:/producto/nuevo";
	}
	
	//para pasar los datos al formulario de actualizar
	@GetMapping("/editar/{id}")
	public String MostrarFormularioEditar(@PathVariable int id, Model model) {
		model.addAttribute("productos", productoServices.listarId(id).orElse(null));
		//listar las categorias para seleccionar en el formulario 
				List<Categorias>ListarCategoria=categoriaServices.Listar();
				model.addAttribute("categoria", ListarCategoria);
		return "Productos/editar_producto";
	}
	
	@PostMapping("/editar/{id}")
	public String ActualizarProducto(@Validated Productos p, RedirectAttributes redirectAttrs) {
		productoServices.save(p);
		redirectAttrs.addFlashAttribute("mensaje", "Producto editado correctamente")
		.addFlashAttribute("clase", "info");
		return "redirect:/producto/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String EliminarProducto(@PathVariable int id, Model model, RedirectAttributes reditecAttrs) {
		productoServices.delete(id);
		reditecAttrs
		.addFlashAttribute("mensaje", "Producto eliminado correctamente")
		.addFlashAttribute("clase", "warning");
		return "redirect:/producto/listar";
	}

}
