package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.service.CategoriaService;

@Controller
@RequestMapping ("/gestioncategoria")
public class CategoriaController {

	@Autowired
	private CategoriaService cService;
	
	@GetMapping ("lista")
	
	public String listar(Model model) {
		model.addAttribute("mensaje","Bienvenido al listado de categorias");
		model.addAttribute("categorias",cService.listar());
		return "categorias/mantCategorias";
	}
}
