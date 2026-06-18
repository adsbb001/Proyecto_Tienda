package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.entity.Producto;
import com.tienda.service.*;

@Controller
@RequestMapping("/gestionproducto")
public class ProductoController {

	@Autowired
	private ProductoService pService;

	@Autowired
	private CategoriaService cService;

	@Autowired
	private MarcaService mService;

	@GetMapping("/lista")
	public String listar(Model model) {

		model.addAttribute("mensaje", "Bienvenido al módulo de Productos");
		model.addAttribute("productos", pService.listar());

		return "producto/mantProducto";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute("producto", new Producto());
		model.addAttribute("categorias", cService.listar());
		model.addAttribute("marcas", mService.listar());

		return "producto/frmProducto";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Producto producto,
						  RedirectAttributes redirect) {

		boolean esNuevo = (producto.getIdProducto() == null);

		pService.guardar(producto);

		if (esNuevo) {
			redirect.addFlashAttribute("MensajeExito",
					"Producto registrado correctamente");
		} else {
			redirect.addFlashAttribute("MensajeExito",
					"Producto actualizado correctamente");
		}

		return "redirect:/gestionproducto/lista";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {

		model.addAttribute("producto", pService.buscar(id));
		model.addAttribute("categorias", cService.listar());
		model.addAttribute("marcas", mService.listar());

		return "producto/frmProducto";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id,
						   RedirectAttributes redirect) {

		pService.eliminar(id);

		redirect.addFlashAttribute("MensajeExito",
				"Producto eliminado correctamente");

		return "redirect:/gestionproducto/lista";
	}
}