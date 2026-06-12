package com.tienda.service;

import java.util.List;
import com.tienda.entity.Proveedor;

public interface ProveedorService {

	List<Proveedor> listar();

	Proveedor buscar(Integer id);

	void guardar(Proveedor proveedor);

	void eliminar(Integer id);
}