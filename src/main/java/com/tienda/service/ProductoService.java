package com.tienda.service;

import java.util.List;

import com.tienda.entity.LoteProducto;
import com.tienda.entity.Producto;

public interface ProductoService {

	List<Producto> listar();

	Producto buscar(Integer id);

	void guardar(Producto producto);

	void eliminar(Integer id);

	List<LoteProducto> listarLotes(Integer idProducto);
	

	
		
List<Producto> listarProductosStockBajo();
    
    List<Producto> listarStockBajoPorCategoria(Integer idCategoria);
    
    long contarProductosStockBajo();
	

	List<Producto> buscarPorFiltros(int categoria, int marca, int filtroStock);

}