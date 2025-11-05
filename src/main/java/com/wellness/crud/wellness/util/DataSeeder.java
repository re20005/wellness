package com.wellness.crud.wellness.util;

import com.wellness.crud.wellness.model.Usuario;
import com.wellness.crud.wellness.repository.UsuarioRepository;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
       /* if (usuarioRepository.findByEmail("admin2@correo.com").isEmpty()) {
            Usuario u = new Usuario();
            u.setNombre("gerson");
            u.setEmail("gerson@correo.com");
            u.setPassword(passwordEncoder.encode("admin123"));
            u.setRol("admin");
            u.setActivo(true);
            u.setFechaCreacion(new Timestamp(System.currentTimeMillis()));

            System.out.println("Creando usuario admin con email: " + u.getEmail());
            usuarioRepository.save(u);
        }*/
    }
}
