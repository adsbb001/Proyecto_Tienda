package com.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.tienda.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
		

	
	@Query("SELECT p FROM Producto p " +
	           "WHERE p.stock <= p.stockMinimo " +
	           "AND p.categoria.estado = 1 " +
	           "ORDER BY p.stock ASC")
	    List<Producto> encontrarProductosStockBajo();

	    @Query("SELECT p FROM Producto p " +
	           "WHERE p.stock <= p.stockMinimo " +
	           "AND p.categoria.estado = 1 " +
	           "AND p.categoria.idCategoria = :idCategoria " +
	           "ORDER BY p.stock ASC")
	    List<Producto> encontrarStockBajoPorCategoria(@Param("idCategoria") Integer idCategoria);

	    @Query("SELECT COUNT(p) FROM Producto p " +
	           "WHERE p.stock <= p.stockMinimo " +
	           "AND p.categoria.estado = 1")
	    long contarProductosStockBajo();



@Query("""  		
		  SELECT p FROM Producto p
         WHERE (:categoria = 0 OR p.categoria.idCategoria = :categoria)
           AND (:marca = 0 OR p.marca.idMarca = :marca)
           AND (:filtroStock = 0 
                OR (:filtroStock = 1 AND p.stock > 0)
                OR (:filtroStock = 2 AND p.stock <= p.stockMinimo))
       """)

 List<Producto> buscarPorFiltros(
         @Param("categoria") int categoria,
         @Param("marca") int marca,
         @Param("filtroStock") int filtroStock
         );
}
	

