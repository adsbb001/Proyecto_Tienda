package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
