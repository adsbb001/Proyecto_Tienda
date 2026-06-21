package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.service.ReporteService;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/stock-bajo")
    public ResponseEntity<byte[]> generarReporte()
            throws Exception {

        byte[] pdf =
                reporteService.generarReporteStockBajo();

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=stock_bajo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}