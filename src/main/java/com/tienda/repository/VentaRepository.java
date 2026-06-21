package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tienda.entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query("SELECT COALESCE(MAX(v.idVenta), 0) + 1 FROM Venta v")
    Integer obtenerSiguienteCodigo();

}