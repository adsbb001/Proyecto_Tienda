package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {	

}
