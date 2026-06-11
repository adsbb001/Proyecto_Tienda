package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.entity.Categoria;
import com.tienda.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private CategoriaRepository repoCategoria;
	
	@Override
	public List<Categoria> listar() {
		
		return repoCategoria.findAll();
	}

}
