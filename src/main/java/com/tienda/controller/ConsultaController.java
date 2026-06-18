package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.service.CategoriaService;
import com.tienda.service.LoteProductoService;
import com.tienda.service.MarcaService;
import com.tienda.service.ProductoService;

@Controller
@RequestMapping("/gestionconsultas")
public class ConsultaController {

    @Autowired
    private ProductoService serviceProducto;

    @Autowired
    private MarcaService serviceMarca;

    @Autowired
    private CategoriaService serviceCategoria;

    @Autowired
    private LoteProductoService serviceLote;

    @GetMapping("/consultas")
    public String irAConsultas(Model model) {
        model.addAttribute("categorias", serviceCategoria.listar());
        model.addAttribute("marcas", serviceMarca.listar());
        model.addAttribute("productos", null); 
        return "consultas/ConsultasTienda"; 
    }

    @GetMapping("/consultas/buscar")
    public String buscarProductos(
            @RequestParam int categoria, 
            @RequestParam int marca, 
            @RequestParam(defaultValue = "0") int filtroStock, 
            Model model) {
        
        model.addAttribute("categorias", serviceCategoria.listar());
        model.addAttribute("marcas", serviceMarca.listar());
        
        model.addAttribute("productos", serviceProducto.buscarPorFiltros(categoria, marca, filtroStock));
        
        model.addAttribute("categoriaSeleccionada", categoria);
        model.addAttribute("marcaSeleccionada", marca);
        model.addAttribute("stockSeleccionado", filtroStock);
        
        return "consultas/ConsultasTienda"; 
    }

    @GetMapping("/lotes")
    public String irALotes(Model model) {
        model.addAttribute("categorias", serviceCategoria.listar());
        model.addAttribute("marcas", serviceMarca.listar());
        model.addAttribute("lotes", null); 
        return "consultas/ConsultasTienda"; 
    }

    @GetMapping("/lotes/buscar")
    public String buscarLotes(
            @RequestParam int categoria, 
            @RequestParam int marca, 
            @RequestParam(defaultValue = "0") int filtroVencimiento, 
            Model model) {
        
        model.addAttribute("categorias", serviceCategoria.listar());
        model.addAttribute("marcas", serviceMarca.listar());
        
        model.addAttribute("lotes", serviceLote.buscarLotesAvanzados(categoria, marca, filtroVencimiento));
        
        model.addAttribute("categoriaSeleccionada", categoria);
        model.addAttribute("marcaSeleccionada", marca);
        model.addAttribute("vencimientoSeleccionado", filtroVencimiento);
        
        return "consultas/ConsultasTienda"; 
    }
}