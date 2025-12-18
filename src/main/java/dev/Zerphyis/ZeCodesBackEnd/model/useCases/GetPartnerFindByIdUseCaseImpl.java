package dev.Zerphyis.ZeCodesBackEnd.model.useCases;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.PartnerNotFoundException;
import dev.Zerphyis.ZeCodesBackEnd.model.interfacesCases.GetPartnerFindByIdUseCase;
import dev.Zerphyis.ZeCodesBackEnd.model.repository.PartnerRepositoryGateway;

import java.util.Objects;
import java.util.UUID;

public class GetPartnerFindByIdUseCaseImpl implements GetPartnerFindByIdUseCase {

    private final PartnerRepositoryGateway repositoryGateway;

    public GetPartnerFindByIdUseCaseImpl(PartnerRepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public Partner execute(UUID id) {
        Objects.requireNonNull(id,"id não pode ser nulo");

        return repositoryGateway.findById(id).orElseThrow(() -> new PartnerNotFoundException("Parceiro não encontrado com id "+ id));
    }
}
