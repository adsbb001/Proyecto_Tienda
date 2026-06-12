package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.entity.Marca;
import com.tienda.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaRepository repoMarca;

	@Override
	public List<Marca> listar() {
		return repoMarca.findAll();
	}

	@Override
	public Marca buscar(Integer id) {
		return repoMarca.findById(id).orElse(null);
	}

	@Override
	public void guardar(Marca marca) {
		repoMarca.save(marca);
	}

	@Override
	public void eliminar(Integer id) {
		repoMarca.deleteById(id);
	}
}