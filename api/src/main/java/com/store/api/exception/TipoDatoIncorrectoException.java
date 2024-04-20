package com.store.api.exception;



public class TipoDatoIncorrectoException extends RuntimeException {

    public TipoDatoIncorrectoException(String mensaje) {
        super(mensaje);
    }

    public TipoDatoIncorrectoException(String mensaje, Exception ex) {
        super(mensaje, ex);
    }


}