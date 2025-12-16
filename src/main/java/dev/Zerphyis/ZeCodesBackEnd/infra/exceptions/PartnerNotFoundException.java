package dev.Zerphyis.ZeCodesBackEnd.infra.exceptions;

public class PartnerNotFoundException extends RuntimeException {
    public PartnerNotFoundException(String message) {
        super(message);
    }
}
