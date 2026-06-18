package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tienda.entity.Proveedor;
import com.tienda.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {

	@Autowired
	private ProveedorRepository repoProveedor;

	@Override
	public List<Proveedor> listar() {
		return repoProveedor.findAll();
	}

	@Override
	public Proveedor buscar(Integer id) {
		return repoProveedor.findById(id).orElse(null);
	}

	@Override
	public void guardar(Proveedor proveedor) {
		repoProveedor.save(proveedor);
	}

	@Override
	public void eliminar(Integer id) {
		Proveedor proveedor=buscar(id);
		if(proveedor!=null) {
			proveedor.setEstado(0);
			repoProveedor.save(proveedor);
		
	}
	}
	
	@Override
	public void actualizar(Proveedor proveedor) {
		repoProveedor.save(proveedor);
		
	}
}