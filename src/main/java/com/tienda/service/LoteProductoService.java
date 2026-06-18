package com.tienda.service;

import java.util.List;

import com.tienda.entity.LoteProducto;

public interface LoteProductoService {

	List<LoteProducto> listar();

	LoteProducto buscar(Integer id);

	void guardar(LoteProducto lote);

	void eliminar(Integer id);
	
	List<LoteProducto> buscarLotesAvanzados(int categoria, int marca, int filtroVencimiento);
}