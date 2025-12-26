package dev.Zerphyis.ZeCodesBackEnd.model.exceptions;

public class NoPartnerAvailableException extends DomainException {
    public NoPartnerAvailableException() {
        super("Nenhum partner disponível para os critérios informados");
    }
}
