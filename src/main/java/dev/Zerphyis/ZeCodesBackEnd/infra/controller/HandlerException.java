package dev.Zerphyis.ZeCodesBackEnd.infra.controller;

import dev.Zerphyis.ZeCodesBackEnd.aplication.dtos.ApiErroResponse;
import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler({
            PartnerNotFoundException.class,
            NoPartnerAvailableException.class
    })
    public ResponseEntity<ApiErroResponse> handleNotFound(RuntimeException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErroResponse(
                        404,
                        "NOT_FOUND",
                        ex.getMessage(),
                        request.getRequestURI(),
                        Instant.now()
                ));
    }


    @ExceptionHandler(PartnerAlreadyExistsException.class)
    public ResponseEntity<ApiErroResponse> handleconflict(RuntimeException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiErroResponse(
                        409,
                        "CONFLICT",
                        ex.getMessage(),
                        request.getRequestURI(),
                        Instant.now()
                ));
    }


    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiErroResponse> handleDomainException(RuntimeException ex, HttpServletRequest request){
        return ResponseEntity.badRequest()
                .body(new ApiErroResponse(
                        400,
                        "BAD_REQUEST",
                        ex.getMessage(),
                        request.getRequestURI(),
                        Instant.now()
                ));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErroResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos");

        return ResponseEntity.badRequest()
                .body(new ApiErroResponse(
                        400,
                        "VALIDATION_ERROR",
                        message,
                        request.getRequestURI(),
                        Instant.now()
                ));
    }



    @ExceptionHandler(RateLimiterExecededException.class)
    public ResponseEntity<ApiErroResponse> handleRateLimit(RateLimiterExecededException ex, HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ApiErroResponse(
                        429,
                        "TOO_MANY_REQUESTS",
                        ex.getMessage(),
                        request.getRequestURI(),
                        Instant.now()
                ));
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErroResponse> handleGeneric(Exception ex, HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErroResponse(
                        500,
                        "INTERNAL_SERVER_ERROR",
                        "Erro interno inesperado",
                        request.getRequestURI(),
                        Instant.now()
                ));
    }

}
