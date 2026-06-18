package com.tienda.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tienda.entity.LoteProducto;

public interface LoteProductoRepository  extends JpaRepository<LoteProducto, Integer> {

@Query("""
        SELECT l FROM LoteProducto l
        WHERE (:categoria = 0 OR l.producto.categoria.idCategoria = :categoria)
          AND (:marca = 0 OR l.producto.marca.idMarca = :marca)
          AND (:filtroVencimiento = 0 
               OR (:filtroVencimiento = 1 AND l.fechaVencimiento < :fechaHoy)
               OR (:filtroVencimiento = 2 AND l.fechaVencimiento >= :fechaHoy 
                                              AND l.fechaVencimiento <= :fechaLimite))
        """)
List<LoteProducto> buscarLotesAvanzados(
        @Param("categoria") int categoria,
        @Param("marca") int marca,
        @Param("filtroVencimiento") int filtroVencimiento,
        @Param("fechaHoy") LocalDate fechaHoy,
        @Param("fechaLimite") LocalDate fechaLimite
        );
	
}
