package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tienda.entity.LoteProducto;
import com.tienda.entity.Producto;
import com.tienda.repository.LoteProductoRepository;
import com.tienda.repository.ProductoRepository;

@Service
public class ProductoServiceImpl
implements ProductoService {

	@Autowired
	private ProductoRepository repoProducto;

	@Autowired
	private LoteProductoRepository repoLote;

	@Override
	public List<Producto> listar() {
		return repoProducto.findAll();
	}

	@Override
	public Producto buscar(Integer id) {
		return repoProducto.findById(id).orElse(null);
	}

	@Override
	public void guardar(Producto producto) {
		repoProducto.save(producto);
	}

	@Override
	public void eliminar(Integer id) {
		repoProducto.deleteById(id);
	}

	@Override
	public List<LoteProducto> listarLotes(Integer idProducto) {

		return repoLote.findAll()
				.stream()
				.filter(l ->
					l.getProducto().getIdProducto()
					.equals(idProducto))
				.toList();
	}

	@Override

	@Transactional (readOnly = true)
	public List<Producto> listarProductosStockBajo() {
		return repoProducto.encontrarProductosStockBajo();
	}

	@Override
	@Transactional (readOnly = true)
	public List<Producto> listarStockBajoPorCategoria(Integer idCategoria) {
		if (idCategoria == null) {
	        return repoProducto.encontrarProductosStockBajo();
	    }
	    return repoProducto.encontrarStockBajoPorCategoria(idCategoria);
	}

	@Override
	@Transactional (readOnly = true)
	public long contarProductosStockBajo() {
		return repoProducto.contarProductosStockBajo();
	}

	public List<Producto> buscarPorFiltros(int categoria, int marca, int filtroStock) {
		return repoProducto.buscarPorFiltros(categoria, marca, filtroStock);
	}


}