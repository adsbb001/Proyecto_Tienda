package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.entity.Compra;
import com.tienda.entity.DetalleCompra;
import com.tienda.repository.CompraRepository;
import com.tienda.repository.DetalleCompraRepository;

@Service
public class CompraServiceImpl
implements CompraService {

	@Autowired
	private CompraRepository repoCompra;

	@Autowired
	private DetalleCompraRepository repoDetalleCompra;

	@Override
	public List<Compra> listar() {
		return repoCompra.findAll();
	}

	@Override
	public Compra buscar(Integer id) {
		return repoCompra.findById(id).orElse(null);
	}

	@Override
	public void guardar(Compra compra) {
		repoCompra.save(compra);
	}

	@Override
	public void eliminar(Integer id) {
		repoCompra.deleteById(id);
	}

	@Override
	public void guardarDetalle(
			DetalleCompra detalle) {

		repoDetalleCompra.save(detalle);
	}

	@Override
	public List<DetalleCompra> listarDetalles() {
		return repoDetalleCompra.findAll();
	}
}