package com.tienda.service;

import java.util.List;

import com.tienda.entity.Categoria;


public interface CategoriaService {

	List<Categoria> listar();

	Categoria buscar(Integer id);

	void guardar(Categoria categoria);
	void actualizar(Categoria categoria);

	void eliminar(Integer id);

}