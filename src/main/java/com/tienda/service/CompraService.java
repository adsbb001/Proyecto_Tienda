package com.tienda.service;

import java.util.List;
import com.tienda.entity.Compra;
import com.tienda.entity.DetalleCompra;

public interface CompraService {

	List<Compra> listar();

	Compra buscar(Integer id);

	void guardar(Compra compra);

	void eliminar(Integer id);

	void guardarDetalle(
			DetalleCompra detalle);

	List<DetalleCompra> listarDetalles();
}