package com.tienda.service;

import java.util.List;
import com.tienda.entity.MetodoPago;

public interface MetodoPagoService {

	List<MetodoPago> listar();

	MetodoPago buscar(Integer id);

	void guardar(MetodoPago metodoPago);

	void eliminar(Integer id);
}