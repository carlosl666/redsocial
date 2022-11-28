package com.caamal.ms.usuarios.service;

import com.caamal.ms.usuarios.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listAllUsuario();
    Optional<Usuario> findByIdUsuario(Long idUsuario);
    Usuario saveUsuario(Usuario usuario);
    void deleteUsuario(Long idUsuario);
    Optional<Usuario> findByEmail (String email);
    Optional<Usuario> findByUsername(String username);
}
