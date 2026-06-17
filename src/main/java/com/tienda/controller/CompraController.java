package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tienda.entity.*;
import com.tienda.service.*;

@Controller
@RequestMapping("/gestioncompra")
public class CompraController {

	@Autowired
	private CompraService cService;

	@Autowired
	private ProveedorService pService;

	@GetMapping("/lista")
	public String listar(Model model) {
        
		model.addAttribute("mensaje", "Bienvenido al módulo de Compras");
		model.addAttribute("compras", cService.listar());
		return "compra/mantCompra";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute("compra", new Compra());
		model.addAttribute("proveedores", pService.listar());

		return "compra/frmCompra";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Compra compra) {

		cService.guardar(compra);
		return "redirect:/gestioncompra/lista";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {

		model.addAttribute("compra", cService.buscar(id));
		model.addAttribute("proveedores", pService.listar());

		return "compra/frmCompra";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {

		cService.eliminar(id);
		return "redirect:/gestioncompra/lista";
	}
}