package com.caamal.ms.usuarios.controller;

import com.caamal.ms.usuarios.service.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;

@RestControllerAdvice
public class ApiExceptionHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(UsuarioException.class)
    public HttpEntity notFound(UsuarioException usuarioException){
        HashMap<String, Object> body = new HashMap<>();
        body.put("id", usuarioException.getId());
        body.put("mensaje", usuarioException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
