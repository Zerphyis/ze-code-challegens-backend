package dev.Zerphyis.ZeCodesBackEnd.infra.exceptions;

public class InvalidGeoDataException extends RuntimeException {
    public InvalidGeoDataException(String message) {
        super(message);
    }
}
