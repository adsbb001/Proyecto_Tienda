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
	SecurityFilterChain	 filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(auth -> auth
				// Permitir acceso público a recursos estáticos
                .requestMatchers("/css/**", "/js/**", "/img/**", "/webjars/**").permitAll()
				.requestMatchers("/login")
				.permitAll()
				
				.requestMatchers("/","/index")
				.authenticated()
				
				.requestMatchers("/gestioncliente/**")
				.hasRole("ADMIN")
				
				.requestMatchers("/gestionproveedor/**")
				.hasAnyRole("ADMIN")
				
				.requestMatchers("/gestioncategoria/**")
				.hasRole("ADMIN")
				
				.requestMatchers("/gestionproducto/**")
				.hasRole("ADMIN")
				
				.requestMatchers("/gestionconsulta/**")
				.hasAnyRole("ADMIN", "VENDEDOR")
				
				.anyRequest()
				.authenticated()
				)
		.formLogin(form -> form
		        .loginPage("/login")

		        .defaultSuccessUrl("/home",
		                true)

		        .failureHandler((request, response, exception) -> {
		            exception.printStackTrace();
		            response.sendRedirect("/login?error");
		        })

		        .permitAll()
		)
		 // 🔴 AGREGA ESTO: Manejador de acceso denegado
        .exceptionHandling(ex -> ex
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    // Redirige al home con un parámetro de error
                    response.sendRedirect("/home?accessDenied=true");
                })
        )
		
		.logout(logout -> logout
				.logoutSuccessUrl("/login"));
		
		return http.build();
	}
	
}
