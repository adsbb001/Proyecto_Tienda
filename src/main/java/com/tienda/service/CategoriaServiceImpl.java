package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tienda.entity.Categoria;
import com.tienda.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repoCategoria;

	@Override
	public List<Categoria> listar() {
		
		return repoCategoria.findAll();
	}

	@Override
	public Categoria buscar(Integer id) {
		
		return repoCategoria.findById(id).orElse(null);
	}

	@Override
	public void guardar(Categoria categoria) {
		repoCategoria.save(categoria);
		
	}

	@Override
	public void eliminar(Integer id) {
		Categoria categoria=buscar(id);
		if(categoria!=null) {
			categoria.setEstado(0);
			repoCategoria.save(categoria);
		
	}
		}

	@Override
	public void actualizar(Categoria categoria) {
		repoCategoria.save(categoria);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> listarActivas() {
		return repoCategoria.listarActivas();
	}
	

}