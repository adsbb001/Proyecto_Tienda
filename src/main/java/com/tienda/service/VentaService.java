package com.tienda.service;

import java.util.List;

import com.tienda.dto.VentaDTO;
import com.tienda.entity.DetalleVenta;
import com.tienda.entity.Venta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VentaService {

	List<Venta> listar();

	Venta buscar(Integer id);

	void guardar(Venta venta);

	void eliminar(Integer id);

	void guardarDetalle(DetalleVenta detalle);

	List<DetalleVenta> listarDetalles();
	
	Venta procesarVenta(VentaDTO dto);
	
	Page<Venta> listarPaginado(Pageable pageable);
	
	void anularVenta(Integer idVenta);
}