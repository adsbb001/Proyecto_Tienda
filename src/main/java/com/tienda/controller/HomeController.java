package com.tienda.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.entity.Usuario;
import com.tienda.repository.UsuarioRepository;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping("/home")
    public String home(Model model, Principal principal ,@RequestParam(value = "accessDenied",required = false) String accessDenied) {
        
        // Obtener el username del usuario autenticado
        String username = principal.getName();
        
        // Buscar el usuario completo en la base de datos
        Usuario usuario = usuarioRepo.findByUsername(username)
                            .orElse(null);
        
        // Pasar el usuario al template
        model.addAttribute("usuario", usuario);
        
     // 🔴 AGREGAR ESTO: Si viene con acceso denegado, agregar mensaje
        if (accessDenied != null) {
            model.addAttribute("errorMessage", "No tienes permisos para acceder a esa sección.");
        }
        
        return "index";
    }
}