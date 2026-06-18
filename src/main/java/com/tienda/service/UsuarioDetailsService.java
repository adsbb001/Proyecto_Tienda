package com.tienda.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.tienda.entity.Usuario;


import com.tienda.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repo.findByUsername(username).orElseThrow(() -> 
		new UsernameNotFoundException("Usuario no encontrado"));
		
		 System.out.println("USUARIO: " + usuario.getUsername());
		    System.out.println("PASSWORD BD: " + usuario.getPassword());
		    System.out.println("ROL: " + usuario.getRol().getNombre());
		
		    System.out.println(
		            new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()
		                .matches("123456", usuario.getPassword())
		        );
		    
		    System.out.println(
		    	    "AUTHORITY: ROLE_" + usuario.getRol().getNombre()
		    	);
		    
		    System.out.println("HASH: " + usuario.getPassword());
		
		return new User(
				usuario.getUsername(),
				usuario.getPassword(),
				Collections.singletonList(
						new SimpleGrantedAuthority("ROLE_" +usuario.getRol().getNombre())));
	}

}
