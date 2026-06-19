package com.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.entity.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;

@Controller
@RequestMapping("/gestionreporte")
public class ReporteInventarioController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/lista")
    public String stockBajo(
            @RequestParam(value = "idCategoria", required = false) Integer idCategoria,
            Model model) {

        // 1. Obtener productos (filtrados o todos)
        List<Producto> productos = productoService.listarStockBajoPorCategoria(idCategoria);

        // 2. Pasar datos a la vista
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categoriaService.listarActivas());
        model.addAttribute("idCategoriaSeleccionada", idCategoria);
        model.addAttribute("totalProductos", productos.size());

        return "reportes/stock-bajo";
    }
}	