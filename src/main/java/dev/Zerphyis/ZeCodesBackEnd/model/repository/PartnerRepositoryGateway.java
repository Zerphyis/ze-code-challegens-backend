    package dev.Zerphyis.ZeCodesBackEnd.model.repository;

    import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;

    import java.util.List;
    import java.util.Optional;
    import java.util.UUID;

    public interface PartnerRepositoryGateway {
        Optional<Partner> findById(UUID id);

        Optional<Partner> findByDocument(String document);

        Partner save(Partner partner);

        Optional<Partner> searchNearest(Double latitude, Double longitude);

        void delete(UUID id);

        List<Partner> findAll();

        List<Partner> findByActive(boolean active);
    }
