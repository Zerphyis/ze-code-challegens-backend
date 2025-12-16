package dev.Zerphyis.ZeCodesBackEnd.model.repository;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;

import java.util.Optional;

public interface PartnerRepositoryGateway {
    Optional<Partner> findById(String id);

    Optional<Partner> findByDocument(String document);

    Partner save(Partner partner);

    Optional<Partner> searchNearest(Double latitude, Double longitude);
}
