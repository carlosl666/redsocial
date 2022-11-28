package com.caamal.ms.oauth.config;

import com.caamal.ms.oauth.service.remote.UsuarioException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
