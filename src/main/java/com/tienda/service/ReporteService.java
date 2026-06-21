package com.tienda.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.entity.Producto;
import com.tienda.repository.ProductoRepository;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class ReporteService {

    @Autowired
    private ProductoRepository productoRepository;

    public byte[] generarReporteStockBajo() throws Exception {

        InputStream reporte = getClass()
                .getResourceAsStream("/reports/stock_bajo.jrxml");

        JasperReport jasperReport =
                JasperCompileManager.compileReport(reporte);

        List<Producto> productos =
                productoRepository.encontrarProductosStockBajo();

        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(productos);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        new HashMap<>(),
                        dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}