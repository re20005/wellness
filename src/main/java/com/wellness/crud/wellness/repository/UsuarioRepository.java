package com.wellness.crud.wellness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wellness.crud.wellness.model.Usuario;
import java.util.Optional;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmailAndActivoTrue(String email);
    List<Usuario> findByRol(String rol);
}


/*package com.wellness.crud.wellness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wellness.crud.wellness.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}*/

