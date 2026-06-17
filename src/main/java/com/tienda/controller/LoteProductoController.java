package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tienda.entity.LoteProducto;
import com.tienda.service.*;

@Controller
@RequestMapping("/gestionlote")
public class LoteProductoController {

	@Autowired
	private LoteProductoService lService;

	@Autowired
	private ProductoService pService;

	@GetMapping("/lista")
	public String listar(Model model) {

		model.addAttribute("lotes", lService.listar());
		return "lote/mantLote";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
        
		model.addAttribute("mensaje", "Bienvenido al módulo de Lotes");
		model.addAttribute("lote", new LoteProducto());
		model.addAttribute("productos", pService.listar());

		return "lote/frmLote";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute LoteProducto lote) {

		lService.guardar(lote);
		return "redirect:/gestionlote/lista";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {

		model.addAttribute("lote", lService.buscar(id));
		model.addAttribute("productos", pService.listar());

		return "lote/frmLote";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {

		lService.eliminar(id);
		return "redirect:/gestionlote/lista";
	}
}