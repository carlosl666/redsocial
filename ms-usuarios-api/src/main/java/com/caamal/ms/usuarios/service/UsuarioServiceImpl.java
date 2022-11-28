package com.caamal.ms.usuarios.service;

import com.caamal.ms.usuarios.entity.Usuario;
import com.caamal.ms.usuarios.repository.UsuarioRepository;
import com.caamal.ms.usuarios.service.remote.PublicacionClienteRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PublicacionClienteRest publicacionClienteRest;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listAllUsuario() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByIdUsuario(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @Override
    @Transactional
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void deleteUsuario(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
        publicacionClienteRest.deletePublicacionByIdUsuario(idUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByUsername(String username){
        return usuarioRepository.findByUsernameIgnoreCase(username);
    }
}
