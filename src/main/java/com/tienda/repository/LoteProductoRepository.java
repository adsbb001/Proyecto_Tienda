package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.LoteProducto;

public interface LoteProductoRepository  extends JpaRepository<LoteProducto, Integer> {
	
}
