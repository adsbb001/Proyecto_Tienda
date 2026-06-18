package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.entity.MetodoPago;
import com.tienda.service.MetodoPagoService;

@Controller
@RequestMapping("/gestionmetodopago")
public class MetodoPagoController {

	@Autowired
	private MetodoPagoService mpService;

	@GetMapping("/lista")
	public String listar(Model model) {
        
		model.addAttribute("mensaje", "Bienvenido al módulo de Métodos de Pago");
		model.addAttribute(
				"metodosPago",
				mpService.listar());

		return "metodopago/mantMetodoPago";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute(
				"metodoPago",
				new MetodoPago());

		return "metodopago/frmMetodoPago";
	}

	@PostMapping("/guardar")
	public String guardar(
			@ModelAttribute MetodoPago metodoPago) {

		mpService.guardar(metodoPago);

		return "redirect:/gestionmetodopago/lista";
	}

	@GetMapping("/editar/{id}")
	public String editar(
			@PathVariable Integer id,
			Model model) {

		model.addAttribute(
				"metodoPago",
				mpService.buscar(id));

		return "metodopago/frmMetodoPago";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(
			@PathVariable Integer id) {

		mpService.eliminar(id);

		return "redirect:/gestionmetodopago/lista";
	}
}