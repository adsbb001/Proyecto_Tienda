package com.tienda.service;

import java.util.List;
import com.tienda.entity.Marca;

public interface MarcaService {

	List<Marca> listar();

	Marca buscar(Integer id);

	void guardar(Marca marca);

	void eliminar(Integer id);
}