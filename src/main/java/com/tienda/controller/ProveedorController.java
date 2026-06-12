package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.entity.Proveedor;
import com.tienda.service.ProveedorService;

@Controller
@RequestMapping("/gestionproveedor")
public class ProveedorController {

	@Autowired
	private ProveedorService pService;

	@GetMapping("/lista")
	public String listar(Model model) {
        
		model.addAttribute("mensaje", "Bienvenido al módulo de Proveedores");
		model.addAttribute(
				"proveedores",
				pService.listar());

		return "proveedor/mantProveedor";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute(
				"proveedor",
				new Proveedor());

		return "proveedor/frmProveedor";
	}

	@PostMapping("/guardar")
	public String guardar(
			@ModelAttribute Proveedor proveedor) {

		pService.guardar(proveedor);

		return "redirect:/gestionproveedor/lista";
	}

	@GetMapping("/editar/{id}")
	public String editar(
			@PathVariable Integer id,
			Model model) {

		model.addAttribute(
				"proveedor",
				pService.buscar(id));

		return "proveedor/frmProveedor";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(
			@PathVariable Integer id) {

		pService.eliminar(id);

		return "redirect:/gestionproveedor/lista";
	}
}