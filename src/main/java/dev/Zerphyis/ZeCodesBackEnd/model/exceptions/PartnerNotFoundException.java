package dev.Zerphyis.ZeCodesBackEnd.model.exceptions;

public class PartnerNotFoundException extends RuntimeException {
    public PartnerNotFoundException(String message) {
        super(message);
    }
}
