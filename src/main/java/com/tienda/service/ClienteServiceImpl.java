package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tienda.entity.Cliente;
import com.tienda.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repoCliente;

	@Override
	public List<Cliente> listar() {
		return repoCliente.findAll();
	}

	@Override
	public Cliente buscar(Integer id) {
		return repoCliente.findById(id).orElse(null);
	}

	@Override
	public void guardar(Cliente cliente) {
		repoCliente.save(cliente);
	}

	@Override
	public void eliminar(Integer id) {
		repoCliente.deleteById(id);
	}
}
