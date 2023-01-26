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
import com.example.demo.Services.CategoriasServices;

@Controller
@RequestMapping("/categoria")
public class CategoriasController {

	@Autowired
	private CategoriasServices categoriasServices;
	
	
	@GetMapping("/mostrar")
	public String MostrarFormularioYListaCategoria(Model model) {
		//cargar el formulario
		model.addAttribute("categorias", new Categorias());
		//cargar la lista decategorias
		List<Categorias>categoria=categoriasServices.Listar();
		model.addAttribute("categoria", categoria);
		return "Categoria/addcategoria";
	}
	
	@PostMapping("/guardar")
	public String GuardarCategoria(@Valid Categorias c, BindingResult result, Model model, RedirectAttributes redirectAttrs) {
		if(result.hasErrors()) {
			return "Categoria/addcategoria";
		}
		//guardar la categoria
		categoriasServices.save(c);
		redirectAttrs
		.addFlashAttribute("mensaje", "Categoria guardada correctamente")
		.addFlashAttribute("clase", "success");
		//listar categorias
		List<Categorias>categoria=categoriasServices.Listar();
		model.addAttribute("categoria", categoria);
		
		return "redirect:/categoria/mostrar";
	}
	
	@GetMapping("/editar/{id}")
	public String MostrarCategoriaFormulario(@PathVariable int id, Model model) {
		model.addAttribute("categorias", categoriasServices.ListaId(id));
		return "Categoria/addcategoria";
	}
	
	@PostMapping("/editar/{id}")
	public String ActualizarCategoria(@Validated Categorias c, Model model, RedirectAttributes redirectAttrs) {
		categoriasServices.save(c);
		redirectAttrs
		.addFlashAttribute("mensaje", "Categoria editada correctamente")
		.addFlashAttribute("clase", "info");
		return "redirect:/categoria/mostrar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String EliminarCategoria(@PathVariable int id, Model model, RedirectAttributes redirectAtrrs) {
		categoriasServices.deleted(id);
		redirectAtrrs
		.addFlashAttribute("mensaje","Categoria eliminada corectamente")
		.addFlashAttribute("clase", "warning");
		return "redirect:/categoria/mostrar";
	}
	
	
}
