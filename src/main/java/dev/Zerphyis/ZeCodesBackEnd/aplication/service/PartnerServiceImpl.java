package dev.Zerphyis.ZeCodesBackEnd.aplication.service;

import dev.Zerphyis.ZeCodesBackEnd.aplication.service.interfaceService.ServicePartner;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.useCases.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

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
    @CachePut(value = "partners", key = "#result.id")
    @CacheEvict(
            value = {"partnersAll", "partnersActive", "partnersInactive"},
            allEntries = true
    )
    public Partner createPartner(Partner partner) {
        return createPartnerUseCase.execute(partner);
    }


    @Override
    @Cacheable(value = "partners", key = "#id")
    public Partner getPartnerById(String id) {
        return getPartnerByIdUseCase.execute(UUID.fromString(id));
    }

    @Override
    @Cacheable("partnersAll")
    public List<Partner> getAllPartners() {
        return getAllPartnerUseCase.execute();
    }

    @Override
    @Cacheable("partnersActive")
    public List<Partner> getActivePartners() {
        return getActivePartnersUseCase.execute();
    }

    @Override
    @Cacheable("partnersInactive")
    public List<Partner> getInactivePartners() {
        return getInactivePartnersUseCase.execute();
    }

    @Override
    public Partner searchNearestPartner(Double latitude, Double longitude) {
        return searchPartnerUseCase.execute(latitude, longitude);
    }

    @Override
    @CacheEvict(
            value = {"partners", "partnersActive", "partnersInactive", "partnersAll"},
            allEntries = true
    )
    public void deactivatePartner(String id) {
        deactivePartnerUseCase.execute(UUID.fromString(id));
    }

    @Override
    @CacheEvict(
            value = {"partners", "partnersActive", "partnersInactive", "partnersAll"},
            allEntries = true
    )
    public void activatePartner(String id) {
        activePartnerUseCase.execute(UUID.fromString(id));
    }
}