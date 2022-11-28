package com.caamal.ms.oauth.service.remote;

public class UsuarioException extends RuntimeException{

    private final String id;

    public UsuarioException(String mensaje, String id){
        super(mensaje);
        this.id = id;
    }

    public static UsuarioException from(String mensaje, String id){
        return new UsuarioException(mensaje, id);
    }

    public String getId(){
        return id;
    }
}
