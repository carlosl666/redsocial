package com.caamal.ms.usuarios.utils;

import com.caamal.ms.usuarios.entity.Usuario;
import com.caamal.ms.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class ApiUtils {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<Map<String, String>> validarRequest(BindingResult bindingResult){
        Map<String, String> mapErrores = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            mapErrores.put(fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(mapErrores);
    }

    public boolean validarEmail(String email, int tipo, Usuario usuario){
        if(tipo == 1){
            return usuarioRepository.findByEmail(email).isPresent();
        }else{
            return !email.equalsIgnoreCase(usuario.getEmail()) && usuarioRepository.findByEmail(email).isPresent();
        }
    }
}
