package com.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tienda.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
		
@Query("""
	     SELECT p FROM Producto p
	     WHERE (:categoria = 0 OR p.categoria.idCategoria = :categoria)
	       AND (:marca = 0 OR p.marca.idMarca = :marca)
	       AND (:filtroStock = 0 
	            OR (:filtroStock = 1 AND p.stock > p.stockMinimo)
	            OR (:filtroStock = 2 AND p.stock <= p.stockMinimo))
	       """)	
List<Producto> buscarPorFiltros(
	    @Param("categoria") int categoria,
	    @Param("marca") int marca,
	    @Param("filtroStock") int filtroStock
	    );
}
	

