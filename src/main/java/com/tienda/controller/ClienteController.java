package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.entity.Cliente;
import com.tienda.service.ClienteService;

@Controller
@RequestMapping("/gestioncliente")
public class ClienteController {

	@Autowired
	private ClienteService cService;

	@GetMapping("/lista")
	public String listar(Model model) {

		model.addAttribute(
				"clientes",
				cService.listar());

		return "cliente/mantCliente";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute(
				"cliente",
				new Cliente());

		return "cliente/frmCliente";
	}

	@PostMapping("/guardar")
	public String guardar(
			@ModelAttribute Cliente cliente) {

		cService.guardar(cliente);

		return "redirect:/gestioncliente/lista";
	}

	@GetMapping("/editar/{id}")
	public String editar(
			@PathVariable Integer id,
			Model model) {

		model.addAttribute(
				"cliente",
				cService.buscar(id));

		return "cliente/frmCliente";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(
			@PathVariable Integer id) {

		cService.eliminar(id);

		return "redirect:/gestioncliente/lista";
	}
}