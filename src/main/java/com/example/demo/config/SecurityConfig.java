package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para desarrollo, considera habilitarlo en producción
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/css/**", "/js/**", "/images/**").permitAll() // Acceso público a index y recursos estáticos
                        .requestMatchers("/insumos").hasRole("ADMINISTRADOR") // Solo para administradores
                        .requestMatchers("/ventanas").hasRole("CLIENTE") // Solo para clientes
                        .requestMatchers("/ventanaoperario").hasRole("OPERARIO") // Solo para operarios
                        .anyRequest().authenticated() // Otras rutas requieren autenticación
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/default", true) // Redirige a /default después del login exitoso
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/index") // Redirige a /index después de logout
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/403") // Página para acceso denegado
                );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Solo para desarrollo
    }
}
