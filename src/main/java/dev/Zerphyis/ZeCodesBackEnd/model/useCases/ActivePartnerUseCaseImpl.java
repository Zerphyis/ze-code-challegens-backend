package dev.Zerphyis.ZeCodesBackEnd.model.useCases;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.PartnerNotFoundException;
import dev.Zerphyis.ZeCodesBackEnd.model.interfacesCases.ActivePartnerUseCase;
import dev.Zerphyis.ZeCodesBackEnd.model.repository.PartnerRepositoryGateway;

import java.util.Objects;
import java.util.UUID;

public class ActivePartnerUseCaseImpl implements ActivePartnerUseCase {
    private final PartnerRepositoryGateway repositoryGateway;

    public ActivePartnerUseCaseImpl(PartnerRepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public void execute(UUID id) {
        Objects.requireNonNull(id, "Id não pode ser nulo");

        Partner partner = repositoryGateway
                .findById(id)
                .orElseThrow(() ->
                        new PartnerNotFoundException(
                                "Parceiro não encontrado para desativação: " + id
                        )
                );

        partner.activate();

        repositoryGateway.save(partner);
    }
}
