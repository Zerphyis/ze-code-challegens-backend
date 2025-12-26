package dev.Zerphyis.ZeCodesBackEnd.model.exceptions;

public class RateLimiterExecededException extends RuntimeException {
    public RateLimiterExecededException(String message) {
        super(message);
    }
}
