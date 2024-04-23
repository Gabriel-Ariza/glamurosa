package com.store.api.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.store.api.repository.entity.Usuario;



/* -- CREAR, LISTAR, ACTUALIZAR  solamente -- */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Optional<Usuario> findByEmail(String email);
}
