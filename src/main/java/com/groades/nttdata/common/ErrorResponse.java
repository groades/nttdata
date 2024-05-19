package com.groades.nttdata.common;
import lombok.Data;

@Data
public class ErrorResponse {
    private String mensaje;

    public ErrorResponse(String message) {
        this.mensaje = message;
    }
}