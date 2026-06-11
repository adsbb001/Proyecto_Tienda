package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

}
