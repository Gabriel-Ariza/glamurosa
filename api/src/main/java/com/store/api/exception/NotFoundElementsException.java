package com.store.api.exception;



public class NotFoundElementsException extends RuntimeException {

    public NotFoundElementsException(String mensaje) {
        super(mensaje);
    }

    public NotFoundElementsException(String mensaje, Exception ex) {
        super(mensaje, ex);
    }


}
