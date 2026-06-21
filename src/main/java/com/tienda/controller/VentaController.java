package com.tienda.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.dto.VentaDTO;
import com.tienda.entity.*;
import com.tienda.service.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/gestionventa")
public class VentaController {

    @Autowired
    private VentaService vService;

    @Autowired
    private ClienteService cService;

    @Autowired
    private MetodoPagoService mpService;

    @Autowired
    private ProductoService pService;

    // ============================================
    // 📋 CRUD BÁSICO
    // ============================================

    @GetMapping("/lista")
    public String listar(
            @RequestParam(value = "mensaje", required = false) String mensaje,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Model model) {

        // Paginación ordenada por fecha descendente
        Pageable pageable = PageRequest.of(page, size, Sort.by("fecha").descending());
        Page<Venta> ventasPage = vService.listarPaginado(pageable);
        List<Venta> ventas = ventasPage.getContent();

        // 📊 Calcular estadísticas (SOLO ventas ACTIVAS)
        List<Venta> todasLasVentas = vService.listar();
        
        // ✅ Filtrar solo ventas activas
        List<Venta> ventasActivas = todasLasVentas.stream()
                .filter(v -> "ACTIVA".equals(v.getEstado()))
                .collect(Collectors.toList());

        long totalVentas = ventasActivas.size();
        BigDecimal montoTotal = ventasActivas.stream()
                .map(Venta::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        long totalProductosVendidos = ventasActivas.stream()
                .flatMap(v -> v.getDetalles().stream())
                .mapToLong(DetalleVenta::getCantidad)
                .sum();
        long ventasAnonimas = ventasActivas.stream()
                .filter(v -> v.getCliente() == null)
                .count();

        // ✅ NUEVO: Total general (coherente con los contadores)
        BigDecimal totalGeneral = ventasActivas.stream()
                .map(Venta::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("ventas", ventas);
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("totalVentas", totalVentas);
        model.addAttribute("montoTotal", montoTotal);
        model.addAttribute("totalProductosVendidos", totalProductosVendidos);
        model.addAttribute("ventasAnonimas", ventasAnonimas);
        model.addAttribute("totalGeneral", totalGeneral);  // ← NUEVO
        
        // Datos para la paginación
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ventasPage.getTotalPages());
        model.addAttribute("totalItems", ventasActivas.size());

        return "venta/mantVenta";
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

    // ============================================
    // 🛒 NUEVO PROCESO DE VENTA (con DTO)
    // ============================================

    @GetMapping("/nuevo")
    public String nuevaVenta(Model model) {
        model.addAttribute("ventaDTO", new VentaDTO());
        model.addAttribute("clientes", cService.listar());
        model.addAttribute("metodosPago", mpService.listar());
        model.addAttribute("productos", pService.listar());
        return "venta/frmVenta";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute VentaDTO ventaDTO,
                          RedirectAttributes ra) {
        try {
            Venta venta = vService.procesarVenta(ventaDTO);
            ra.addFlashAttribute("mensaje", "✅ Venta registrada correctamente.");
            // ✅ CAMBIO AQUÍ: Redirigir al voucher en lugar de /nuevo
            return "redirect:/gestionventa/voucher/" + venta.getIdVenta();
        } catch (Exception e) {
            ra.addFlashAttribute("error", "❌ " + e.getMessage());
            return "redirect:/gestionventa/nuevo";
        }
    }
    
    @GetMapping("/voucher/{id}")
    public String verVoucher(@PathVariable Integer id, Model model) {
        Venta venta = vService.buscar(id);
        
        if (venta == null) {
            return "redirect:/gestionventa/lista";
        }
        
        // ✅ NUEVO: Obtener el usuario autenticado actual
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        model.addAttribute("venta", venta);
        model.addAttribute("vendedor", username);  // ← NUEVO
        return "venta/voucher";
    }
    
    // ============================================
    // 🚫 ANULAR VENTA
    // ============================================
    
    @GetMapping("/anular/{id}")
    public String anular(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            vService.anularVenta(id);
            ra.addFlashAttribute("mensaje", "✅ Venta anulada correctamente. Stock reintegrado.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "❌ " + e.getMessage());
        }
        return "redirect:/gestionventa/lista";
    }
}