package com.tienda.service;

import java.util.List;
import com.tienda.entity.Cliente;

public interface ClienteService {

	List<Cliente> listar();

	Cliente buscar(Integer id);

	void guardar(Cliente cliente);

	void eliminar(Integer id);
}