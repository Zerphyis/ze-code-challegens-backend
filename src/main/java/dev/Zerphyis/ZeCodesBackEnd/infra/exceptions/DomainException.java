package dev.Zerphyis.ZeCodesBackEnd.infra.exceptions;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
