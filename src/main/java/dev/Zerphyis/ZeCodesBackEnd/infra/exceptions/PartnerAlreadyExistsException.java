package dev.Zerphyis.ZeCodesBackEnd.infra.exceptions;

public class PartnerAlreadyExistsException extends RuntimeException {
    public PartnerAlreadyExistsException(String message) {
        super(message);
    }
}
