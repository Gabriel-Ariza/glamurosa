package com.store.api.service.impl;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.store.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class JWTUserDetailService implements UserDetailsService {


    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.usuarioRepository.findByEmail(username)
            .map(usuario -> {
                final var authorities = List.of(new SimpleGrantedAuthority(usuario.getRol().name()));
                return new User(usuario.getEmail(), usuario.getContrasena(), authorities);

            }).orElseThrow(() -> new UsernameNotFoundException("No se encontró un Usuario con el Email: " + username));                
    }


}
