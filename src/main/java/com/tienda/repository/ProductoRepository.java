package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
		
}
