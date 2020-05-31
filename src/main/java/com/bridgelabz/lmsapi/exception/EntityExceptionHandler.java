package com.bridgelabz.lmsapi.exception;

import com.bridgelabz.lmsapi.response.ErrorMessageDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class EntityExceptionHandler extends ResponseEntityExceptionHandler {
    //Custom exception in global exception handler
    @ExceptionHandler(LmsApiApplicationException.class)
    public final ResponseEntity<ErrorMessageDTO> handleAnyException(Exception ex, WebRequest request) {
        ErrorMessageDTO errorObj = new ErrorMessageDTO(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //MethodArgumentNotValidException
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessageDTO errorObj = new ErrorMessageDTO(new Date(), "From method argument not valid exception",
                request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    //HttpRequestMethodNotSupportedException
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessageDTO errorObj = new ErrorMessageDTO(new Date(), "FromHttpRequestMethodNotSupportedException-Method not allowed",
                request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }

}
