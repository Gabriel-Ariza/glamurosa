package com.store.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.dao.DataIntegrityViolationException;
import com.store.api.exception.*;

// Importa la clase ErrorResponse correcta
import com.store.api.exception.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {


    // Manejador para la excepción TipoDatoIncorrectoException
    @ExceptionHandler(TipoDatoIncorrectoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleTipoDatoIncorrectoException(TipoDatoIncorrectoException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }


    // Manejador para la excepción NombreInvalidoException
    @ExceptionHandler(NombreInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNombreInvalidoException(NombreInvalidoException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }


    // Manejador para la excepción NotFoundElementsException
    @ExceptionHandler(NotFoundElementsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleObjetoNotFoundException(NotFoundElementsException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }


    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleNullPointerException(NullPointerException ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "se intento acceder a un objeto a través de una referencia nula");
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), "La integridad de la base de datos fue violada en un constraint");
    }


}