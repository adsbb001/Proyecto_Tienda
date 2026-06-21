package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tienda.entity.*;
import com.tienda.service.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/gestionventa")
public class VentaController {

	@Autowired
	private VentaService vService;

	@Autowired
	private ClienteService cService;

	@Autowired
	private MetodoPagoService mpService;
	
	@Autowired
	private ProductoService pService;

	@Autowired
	private CategoriaService catService;

	@GetMapping("/lista")
	public String listar(Model model) {
        
		model.addAttribute("mensaje", "Bienvenido al módulo de Ventas");
		model.addAttribute("ventas", vService.listar());
		return "venta/mantVenta";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

	    model.addAttribute("venta", new Venta());
	    model.addAttribute("codigoVenta", vService.obtenerSiguienteCodigo());

	    model.addAttribute("clientes", cService.listar());
	    model.addAttribute("metodosPago", mpService.listar());

	    model.addAttribute("productos", pService.listar());
	    model.addAttribute("categorias", catService.listar());

	    return "venta/frmVenta";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Venta venta,
	        @RequestParam(required = false) List<Integer> productoIds,
	        @RequestParam(required = false) List<Integer> cantidades,
	        @RequestParam(required = false) List<BigDecimal> precios,
	        @RequestParam(required = false) List<BigDecimal> subtotales) {

	    if (venta.getFecha() == null) {
	        venta.setFecha(java.time.LocalDateTime.now());
	    }

	    if (venta.getCliente() != null && venta.getCliente().getIdCliente() == null) {
	        venta.setCliente(null);
	    }

	    vService.guardar(venta);

	    if (productoIds != null) {
	        for (int i = 0; i < productoIds.size(); i++) {

	            Producto producto = pService.buscar(productoIds.get(i));

	            DetalleVenta detalle = new DetalleVenta();
	            detalle.setVenta(venta);
	            detalle.setProducto(producto);
	            detalle.setCantidad(cantidades.get(i));
	            detalle.setPrecioVenta(precios.get(i));
	            detalle.setSubtotal(subtotales.get(i));

	            vService.guardarDetalle(detalle);

	            producto.setStock(producto.getStock() - cantidades.get(i));
	            pService.guardar(producto);
	        }
	    }

	    return "redirect:/gestionventa/lista";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {

		model.addAttribute("venta", vService.buscar(id));

		model.addAttribute("clientes", cService.listar());
		model.addAttribute("metodosPago", mpService.listar());

		return "venta/frmVenta";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {

		vService.eliminar(id);
		return "redirect:/gestionventa/lista";
	}
}