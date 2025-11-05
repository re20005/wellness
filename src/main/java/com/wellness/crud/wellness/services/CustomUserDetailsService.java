package com.wellness.crud.wellness.services;

import com.wellness.crud.wellness.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.stream.Collectors;
import com.wellness.crud.wellness.services.CustomUserDetailsService;
import com.wellness.crud.wellness.config.JwtUtil;
import com.wellness.crud.wellness.repository.UsuarioRepository;
import com.wellness.crud.wellness.model.Usuario;
import java.util.List;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

    return new User(
        usuario.getEmail(),
        usuario.getPassword(),
        List.of(new SimpleGrantedAuthority(usuario.getRol()))
    );
}

}
