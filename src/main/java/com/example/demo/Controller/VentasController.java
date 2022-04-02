package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Modelo.ProductoParaVender;
import com.example.demo.Modelo.Productos;
import com.example.demo.Modelo.ProductosVentas;
import com.example.demo.Modelo.Ventas;
import com.example.demo.Repository.ProductosRepository;
import com.example.demo.Services.ProductosServices;
import com.example.demo.Services.ProductosVentasServices;
import com.example.demo.Services.VentasServices;


@Controller
@RequestMapping("/vender")
public class VentasController {

	 @Autowired
	    private ProductosRepository productosRepository;
	    @Autowired
	    private ProductosServices productoServices;
	    @Autowired
	    private VentasServices ventaServices;
	    @Autowired
	    private ProductosVentasServices productosVendidoServices;
		

	    @PostMapping(value = "/quitar/{indice}")
	    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
	        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
	        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
	            carrito.remove(indice);
	            this.guardarCarrito(carrito, request);
	        }
	        return "redirect:/vender/";
	    }

	    private void limpiarCarrito(HttpServletRequest request) {
	        this.guardarCarrito(new ArrayList<>(), request);
	    }

	    @GetMapping(value = "/limpiar")
	    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
	        this.limpiarCarrito(request);
	        redirectAttrs
	                .addFlashAttribute("mensaje", "Venta cancelada")
	                .addFlashAttribute("clase", "info");
	        return "redirect:/vender/";
	    }

	    @PostMapping(value = "/terminar")
	    public String terminarVenta(Model model,@Validated Ventas v, HttpServletRequest request, RedirectAttributes redirectAttrs) {
	        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
	        // Si no hay carrito o está vacío, regresamos inmediatamente
	        if (carrito == null || carrito.size() <= 0) {
	            return "redirect:/vender/";
	        }
	        
	        ventaServices.save(v);
	        //Venta v = ventasRepository.save(new Venta());
	        // Recorrer el carrito
	        for (ProductoParaVender productoParaVender : carrito) {
	            // Obtener el producto fresco desde la base de datos
	            //Producto p = productosRepository.findById(productoParaVender.getId()).orElse(null);
	        	Productos p= productoServices.listarId(productoParaVender.getId()).orElse(null);
	            if (p == null) continue; // Si es nulo o no existe, ignoramos el siguiente código con continue
	            // Le restamos existencia
	            p.restarExistencia(productoParaVender.getCantidad());
	            // Lo guardamos con la existencia ya restada
	            productoServices.save(p);
	            // Creamos un nuevo producto que será el que se guarda junto con la venta
	            ProductosVentas productoVendido = new ProductosVentas(productoParaVender.getPrecio(),productoParaVender.getCantidad(),productoParaVender.getTotal(),p,v);
	            // Y lo guardamos
	            productosVendidoServices.save(productoVendido);
	        }

	        // Al final limpiamos el carrito
	        this.limpiarCarrito(request);
	        // e indicamos una venta exitosa
	        redirectAttrs
	                .addFlashAttribute("mensaje", "Venta realizada correctamente")
	                .addFlashAttribute("clase", "success");
	        return "redirect:/vender/";
	    }

	    @GetMapping(value = "/")
	    public String interfazVender(Model model, HttpServletRequest request) {
	        model.addAttribute("producto", new Productos());
	        model.addAttribute("ventas", new Ventas());
	        float total = 0;
	        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
	        for (ProductoParaVender p: carrito) total += p.getTotal();
	        model.addAttribute("total", total);
	        return "vender/vender";
	    }

	    private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
	        @SuppressWarnings("unchecked")
	        
			ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession().getAttribute("carrito");
	        if (carrito == null) {
	            carrito = new ArrayList<>();
	        }
	        return carrito;
	    }

	    private void guardarCarrito(ArrayList<ProductoParaVender> carrito, HttpServletRequest request) {
	        request.getSession().setAttribute("carrito", carrito);
	    }

	    @PostMapping(value = "/agregar")
	    public String agregarAlCarrito(@ModelAttribute Productos producto, HttpServletRequest request, RedirectAttributes redirectAttrs) {
	        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
	        //buscar por id
	        Productos productoBuscadoPorID = productosRepository.findFirstById(producto.getId());
	        if (productoBuscadoPorID == null) {
	            redirectAttrs
	                    .addFlashAttribute("mensaje", "El producto con el ID " + producto.getId() + " no existe")
	                    .addFlashAttribute("clase", "warning");
	            return "redirect:/vender/";
	        }
	        if (productoBuscadoPorID.sinExistencia()) {
	            redirectAttrs
	                    .addFlashAttribute("mensaje", "El producto está agotado")
	                    .addFlashAttribute("clase", "warning");
	            return "redirect:/vender/";
	        }
	        boolean encontrado = false;
	
	        for (ProductoParaVender productoParaVenderActual : carrito) {
	            if (productoParaVenderActual.getId()==productoBuscadoPorID.getId()) {
	        
	                productoParaVenderActual.aumentarCantidad();
	                encontrado = true;
	           
	                break;
	            }
	        }
	        if (!encontrado) {
	        	carrito.add(new ProductoParaVender(productoBuscadoPorID.getId(),productoBuscadoPorID.getNombre(),productoBuscadoPorID.getPrecio(),productoBuscadoPorID.getStock(), 1));
		        
	        	//carrito.add(new ProductoParaVender(productoBuscadoPorCodigo.getId(),productoBuscadoPorCodigo.getNombre(),productoBuscadoPorCodigo.getReferencia(),productoBuscadoPorCodigo.getPrecio(),productoBuscadoPorCodigo.getPeso(),productoBuscadoPorCodigo.getCategoria(), productoBuscadoPorCodigo.getStock(),productoBuscadoPorCodigo.getFechaCreacion(), 1));
	        }
	        this.guardarCarrito(carrito, request);
	        return "redirect:/vender/";
	    }
	    
	    
	    @GetMapping(value ="/ventas")
	    public String mostrarVentas(Model model) {
	    	List<Ventas>venta=ventaServices.listar();
			model.addAttribute("venta", venta);
	        return "vender/ver_ventas";
	    }
}
