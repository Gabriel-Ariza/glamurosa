package com.store.api.exception;



public class NombreInvalidoException extends RuntimeException {

    public NombreInvalidoException(String mensaje) {
        super(mensaje);
    }

    public NombreInvalidoException(String mensaje, Exception ex) {
        super(mensaje, ex);
    }


}
