package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		model.addAttribute("mensaje","Bienvenido al módulo de categorias");
		model.addAttribute("categorias", cService.listar());
		model.addAttribute("categoria",new Categoria());
		return "categorias/mantCategorias";
		
	}


	@PostMapping("/registrar")
	public String registrarCategoria(Categoria categoria,RedirectAttributes redirect) {
		cService.guardar(categoria);
		redirect.addFlashAttribute("MensajeExito","Categoria registrada correctamente");
		return "redirect:/gestioncategoria/lista";
	}

	@GetMapping("/buscar/{id}")
	@ResponseBody
	public Categoria buscarPorId(@PathVariable Integer id) {
		return cService.buscar(id);
	}

	@PostMapping("/actualizar")
	public String actualizarCategoria(Categoria categoria,RedirectAttributes redirect) {

		cService.actualizar(categoria);
		redirect.addFlashAttribute("MensajeExito","Categoria actualizada correctamente");
		return "redirect:/gestioncategoria/lista";
		
		
	}
	
	@GetMapping ("/desactivar/{id}")
	public String desactivarEstado(@PathVariable Integer id,RedirectAttributes redirect) {
		cService.eliminar(id);
		redirect.addFlashAttribute("MensajeExito","Estado de Categoria desactivado");
		return "redirect:/gestioncategoria/lista";
	}
}