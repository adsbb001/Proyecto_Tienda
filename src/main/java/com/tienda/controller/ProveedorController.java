package com.tienda.controller;

import com.tienda.service.ProveedorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.entity.Proveedor;


@Controller
@RequestMapping("/gestionproveedor")
public class ProveedorController {

	@Autowired
	private  ProveedorServiceImpl pService;
	

	@GetMapping("/lista")
	public String listar(Model model) {
		model.addAttribute("mensaje","Bienvenido al módulo de gestión de Proveedores");
		model.addAttribute("proveedores", pService.listar());
		
		model.addAttribute("proveedor", new Proveedor());
		return "proveedores/mantProveedor";
	}

	@PostMapping("/registrar")
	public String registrar(Proveedor proveedor,RedirectAttributes redirect) {
		pService.guardar(proveedor);
		redirect.addFlashAttribute("MensajeExito","Proveedor registrado correctamente");
		return "redirect:/gestionproveedor/lista";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Proveedor proveedor) {

		pService.guardar(proveedor);

		return "redirect:/gestionproveedor/lista";
	}

	@GetMapping("/buscar/{id}")
	@ResponseBody
	public Proveedor buscarPorId(@PathVariable Integer id) {
		return pService.buscar(id);
	}
	
	@PostMapping ("/actualizar")
	public String actualizar(Proveedor proveedor,RedirectAttributes redirect) {
		pService.actualizar(proveedor);
		redirect.addFlashAttribute("MensajeExito","Proveedor actualizado correctamente");
		return "redirect:/gestionproveedor/lista";
	}

	@GetMapping("/eliminar/{id}")
	public String desactivar(@PathVariable Integer id,RedirectAttributes redirect) {
		pService.eliminar(id);
		redirect.addFlashAttribute("MensajeExito","Proveedor desactivado correctamente");
		return "redirect:/gestionproveedor/lista";
	}
}