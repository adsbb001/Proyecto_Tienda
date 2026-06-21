package com.tienda.service;

import java.util.List;

import com.tienda.entity.DetalleVenta;
import com.tienda.entity.Venta;

public interface VentaService {

	List<Venta> listar();

	Venta buscar(Integer id);

	void guardar(Venta venta);

	void eliminar(Integer id);

	void guardarDetalle(DetalleVenta detalle);

	List<DetalleVenta> listarDetalles();
	
	Integer obtenerSiguienteCodigo();
}