package dev.Zerphyis.ZeCodesBackEnd.aplication.service;

import dev.Zerphyis.ZeCodesBackEnd.aplication.service.interfaceService.ServicePartner;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.useCases.*;

import java.util.List;
import java.util.UUID;

public class PartnerServiceImpl implements ServicePartner {
    private final CreatePartnerUseCaseImpl createPartnerUseCase;
    private final GetPartnerFindByIdUseCaseImpl getPartnerByIdUseCase;
    private final SearchPartnerUseCaseImpl searchPartnerUseCase;
    private final GetAllPartnerUseCaseImpl getAllPartnerUseCase;
    private final GetActivePartnersUseCaseImpl getActivePartnersUseCase;
    private final GetInactivePartnersUseCaseImpl getInactivePartnersUseCase;
    private final DeactivePartnerUseCaseImpl deactivePartnerUseCase;
    private final ActivePartnerUseCaseImpl activePartnerUseCase;

    public PartnerServiceImpl(
            CreatePartnerUseCaseImpl createPartnerUseCase,
            GetPartnerFindByIdUseCaseImpl getPartnerByIdUseCase,
            SearchPartnerUseCaseImpl searchPartnerUseCase,
            GetAllPartnerUseCaseImpl getAllPartnerUseCase,
            GetActivePartnersUseCaseImpl getActivePartnersUseCase,
            GetInactivePartnersUseCaseImpl getInactivePartnersUseCase,
            DeactivePartnerUseCaseImpl deactivePartnerUseCase,
            ActivePartnerUseCaseImpl activePartnerUseCase
    ) {
        this.createPartnerUseCase = createPartnerUseCase;
        this.getPartnerByIdUseCase = getPartnerByIdUseCase;
        this.searchPartnerUseCase = searchPartnerUseCase;
        this.getAllPartnerUseCase = getAllPartnerUseCase;
        this.getActivePartnersUseCase = getActivePartnersUseCase;
        this.getInactivePartnersUseCase = getInactivePartnersUseCase;
        this.deactivePartnerUseCase = deactivePartnerUseCase;
        this.activePartnerUseCase = activePartnerUseCase;
    }

    @Override
    public Partner createPartner(Partner partner) {
        return createPartnerUseCase.execute(partner);
    }

    @Override
    public Partner getPartnerById(String id) {
        return getPartnerByIdUseCase.execute(UUID.fromString(id));
    }

    @Override
    public List<Partner> getAllPartners() {
        return getAllPartnerUseCase.execute();
    }

    @Override
    public List<Partner> getActivePartners() {
        return getActivePartnersUseCase.execute();
    }

    @Override
    public List<Partner> getInactivePartners() {
        return getInactivePartnersUseCase.execute();
    }



    @Override
    public Partner searchNearestPartner(Double latitude, Double longitude) {
        return searchPartnerUseCase.execute(latitude, longitude);
    }

    @Override
    public void deactivatePartner(String id) {
        deactivePartnerUseCase.execute(UUID.fromString(id));
    }

    @Override
    public void activatePartner(String id) {
        activePartnerUseCase.execute(UUID.fromString(id));
    }
}
