package dev.Zerphyis.ZeCodesBackEnd.model.useCases;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.PartnerAlreadyExistsException;
import dev.Zerphyis.ZeCodesBackEnd.model.interfacesCases.CreatePartnerUseCase;
import dev.Zerphyis.ZeCodesBackEnd.model.repository.PartnerRepositoryGateway;

import java.util.Objects;

public class CreatePartnerUseCaseImpl implements CreatePartnerUseCase {

    private final PartnerRepositoryGateway repositoryGateway;

    public CreatePartnerUseCaseImpl(PartnerRepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public Partner execute(Partner partner) {
        Objects.requireNonNull(partner, "Partner não pode ser nulo");

        repositoryGateway.findById(partner.getId()).
                ifPresent(existing -> {
            throw new PartnerAlreadyExistsException("Parceiro com Id existente já" + partner.getId());
        });

        repositoryGateway
                .findByDocument(partner.getDocument())
                .ifPresent(existing -> {
                    throw new PartnerAlreadyExistsException(
                            "Parceiro com document já existente: " + partner.getDocument()
                    );
                });

        return repositoryGateway.save(partner);
    }

}
