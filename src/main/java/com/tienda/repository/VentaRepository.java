package com.tienda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tienda.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    
    // Nuevo método con paginación
    Page<Venta> findAll(Pageable pageable);
}