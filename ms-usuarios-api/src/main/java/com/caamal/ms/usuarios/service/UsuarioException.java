package com.caamal.ms.usuarios.service;

public class UsuarioException extends RuntimeException{

    private final String username;

    public UsuarioException(String mensaje, String username){
        super(mensaje);
        this.username = username;
    }

    public static UsuarioException from(String mensaje, String username){
        return new UsuarioException(mensaje, username);
    }

    public String getId(){
        return username;
    }
}
