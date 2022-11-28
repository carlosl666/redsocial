package com.caamal.ms.usuarios.vo;

public class ApiResponse {
    private String mensaje;
    private String codigo;
    private Object detalle;

    public ApiResponse(String mensaje, String codigo, Object detalle) {
        super();
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.detalle = detalle;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Object getDetalle() {
        return detalle;
    }

    public void setDetalle(Object detalle) {
        this.detalle = detalle;
    }
}
