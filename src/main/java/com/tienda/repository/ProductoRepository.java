package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.tienda.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
		
	
	@Query("SELECT p FROM Producto p " +
	           "WHERE p.stock <= p.stockMinimo " +
	           "ORDER BY p.stock ASC")
	    List<Producto> encontrarProductosStockBajo();
	
	
	@Query("SELECT p FROM Producto p " +
	           "WHERE p.stock <= p.stockMinimo " +
	           "AND p.categoria.idCategoria = :idCategoria " +
	           "ORDER BY p.stock ASC")
	    List<Producto> encontrarStockBajoPorCategoria(@Param("idCategoria") Integer idCategoria);
	
	
	@Query("SELECT COUNT(p) FROM Producto p WHERE p.stock <= p.stockMinimo")
    long contarProductosStockBajo();
}
