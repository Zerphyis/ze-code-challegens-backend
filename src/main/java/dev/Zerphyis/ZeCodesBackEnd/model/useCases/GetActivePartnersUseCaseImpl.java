package dev.Zerphyis.ZeCodesBackEnd.model.useCases;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.interfacesCases.GetAllPartnerUseCase;
import dev.Zerphyis.ZeCodesBackEnd.model.repository.PartnerRepositoryGateway;

import java.util.List;

public class GetActivePartnersUseCaseImpl implements GetAllPartnerUseCase {
    private final PartnerRepositoryGateway repositoryGateway;

    public GetActivePartnersUseCaseImpl(PartnerRepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public List<Partner> execute() {
        return repositoryGateway.findByActive(true);
    }
}
