package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tienda.entity.Categoria;
import com.tienda.service.CategoriaService;

@Controller
@RequestMapping("/gestioncategoria")
public class CategoriaController {

	@Autowired
	private CategoriaService cService;

	@GetMapping("/lista")
	public String listar(Model model) {
        
		model.addAttribute("mensaje","Bienvenido al listado de categorias");
		model.addAttribute("categorias", cService.listar());
		return "categoria/mantCategoria";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute("categoria", new Categoria());
		return "categoria/frmCategoria";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Categoria categoria) {

		cService.guardar(categoria);
		return "redirect:/gestioncategoria/lista";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {

		model.addAttribute("categoria", cService.buscar(id));
		return "categoria/frmCategoria";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {

		cService.eliminar(id);
		return "redirect:/gestioncategoria/lista";
	}
}