package com.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.tienda.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {	

	@Query("SELECT c FROM Categoria c WHERE c.estado=1 ORDER BY c.nombre ASC")
	List<Categoria>listarActivas();
}
