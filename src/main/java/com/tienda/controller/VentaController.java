package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tienda.entity.*;
import com.tienda.service.*;

@Controller
@RequestMapping("/gestionventa")
public class VentaController {

	@Autowired
	private VentaService vService;

	@Autowired
	private ClienteService cService;

	@Autowired
	private MetodoPagoService mpService;

	@GetMapping("/lista")
	public String listar(Model model) {

		model.addAttribute("ventas", vService.listar());
		return "venta/mantVenta";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute("venta", new Venta());

		model.addAttribute("clientes", cService.listar());
		model.addAttribute("metodosPago", mpService.listar());

		return "venta/frmVenta";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Venta venta) {

		vService.guardar(venta);
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