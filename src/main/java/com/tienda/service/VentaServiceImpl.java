package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.entity.DetalleVenta;
import com.tienda.entity.Venta;
import com.tienda.repository.DetalleVentaRepository;
import com.tienda.repository.VentaRepository;

@Service
public class VentaServiceImpl
implements VentaService {

	@Autowired
	private VentaRepository repoVenta;

	@Autowired
	private DetalleVentaRepository repoDetalleVenta;

	@Override
	public List<Venta> listar() {
		return repoVenta.findAll();
	}

	@Override
	public Venta buscar(Integer id) {
		return repoVenta.findById(id).orElse(null);
	}

	@Override
	public void guardar(Venta venta) {
		repoVenta.save(venta);
	}

	@Override
	public void eliminar(Integer id) {
		repoVenta.deleteById(id);
	}

	@Override
	public void guardarDetalle(
			DetalleVenta detalle) {

		repoDetalleVenta.save(detalle);
	}

	@Override
	public List<DetalleVenta> listarDetalles() {
		return repoDetalleVenta.findAll();
	}

	@Override
	public Integer obtenerSiguienteCodigo() {
	    return repoVenta.obtenerSiguienteCodigo();
	}
}