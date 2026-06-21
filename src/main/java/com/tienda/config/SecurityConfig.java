package com.tienda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                // Recursos estáticos
                .requestMatchers("/css/**", "/js/**", "/img/**", "/webjars/**").permitAll()
                .requestMatchers("/login").permitAll()

                // Home (requiere estar autenticado)
                .requestMatchers("/", "/index").authenticated()

                // Módulos solo para ADMIN
                .requestMatchers("/gestioncliente/**").hasRole("ADMIN")
                .requestMatchers("/gestionproveedor/**").hasRole("ADMIN")
                .requestMatchers("/gestioncategoria/**").hasRole("ADMIN")
                .requestMatchers("/gestionproducto/**").hasRole("ADMIN")

                // ✅ NUEVO: Ventas - ADMIN y VENDEDOR
                .requestMatchers("/gestionventa/**").hasAnyRole("ADMIN", "VENDEDOR")

                // Consultas - ADMIN y VENDEDOR
                .requestMatchers("/gestionconsulta/**").hasAnyRole("ADMIN", "VENDEDOR")

                // Todo lo demás requiere autenticación
                .anyRequest().authenticated()
        )
        .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureHandler((request, response, exception) -> {
                    exception.printStackTrace();
                    response.sendRedirect("/login?error");
                })
                .permitAll()
        )
        .exceptionHandling(ex -> ex
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/home?accessDenied=true");
                })
        )
        .logout(logout -> logout
                .logoutSuccessUrl("/login"));

        return http.build();
    }
}