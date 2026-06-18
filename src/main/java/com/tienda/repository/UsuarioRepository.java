package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.tienda.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByUsername(String username);
}
