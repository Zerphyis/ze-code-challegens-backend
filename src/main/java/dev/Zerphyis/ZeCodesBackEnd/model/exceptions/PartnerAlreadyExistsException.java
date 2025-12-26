package dev.Zerphyis.ZeCodesBackEnd.model.exceptions;

public class PartnerAlreadyExistsException extends DomainException {
    public PartnerAlreadyExistsException(String document) {
        super("Já existe um partner cadastrado com o documento: " + document);
    }
}
