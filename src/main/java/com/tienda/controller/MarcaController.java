package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.entity.Marca;
import com.tienda.service.MarcaService;

@Controller
@RequestMapping("/gestionmarca")
public class MarcaController {

	@Autowired
	private MarcaService mService;
  
	@GetMapping("/lista")
	public String listar(Model model) {
        
		model.addAttribute("mensaje", "Bienvenido al módulo de Marcas");
		model.addAttribute(
				"marcas",
				mService.listar());

		return "marca/mantMarca";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute(
				"marca",
				new Marca());

		return "marca/frmMarca";
	}

	@PostMapping("/guardar")
	public String guardar(
			@ModelAttribute Marca marca) {

		mService.guardar(marca);

		return "redirect:/gestionmarca/lista";
	}

	@GetMapping("/editar/{id}")
	public String editar(
			@PathVariable Integer id,
			Model model) {

		model.addAttribute(
				"marca",
				mService.buscar(id));

		return "marca/frmMarca";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(
			@PathVariable Integer id) {

		mService.eliminar(id);

		return "redirect:/gestionmarca/lista";
	}
}