package com.caamal.ms.usuarios.repository;

import com.caamal.ms.usuarios.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsernameIgnoreCase(String username);
}
