package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

}
