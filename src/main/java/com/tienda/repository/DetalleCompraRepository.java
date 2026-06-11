package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.DetalleCompra;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Integer> {

}
