package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.entity.MetodoPago;
import com.tienda.repository.MetodoPagoRepository;

@Service
public class MetodoPagoServiceImpl
implements MetodoPagoService {

	@Autowired
	private MetodoPagoRepository repoMetodoPago;

	@Override
	public List<MetodoPago> listar() {
		return repoMetodoPago.findAll();
	}

	@Override
	public MetodoPago buscar(Integer id) {
		return repoMetodoPago.findById(id).orElse(null);
	}

	@Override
	public void guardar(MetodoPago metodoPago) {
		repoMetodoPago.save(metodoPago);
	}

	@Override
	public void eliminar(Integer id) {
		repoMetodoPago.deleteById(id);
	}
}