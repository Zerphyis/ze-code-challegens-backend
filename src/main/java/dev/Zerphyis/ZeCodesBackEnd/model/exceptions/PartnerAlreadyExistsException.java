package dev.Zerphyis.ZeCodesBackEnd.model.exceptions;

public class PartnerAlreadyExistsException extends RuntimeException {
    public PartnerAlreadyExistsException(String message) {
        super(message);
    }
}
