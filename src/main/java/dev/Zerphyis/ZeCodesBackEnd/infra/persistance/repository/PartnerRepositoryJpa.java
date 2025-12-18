package dev.Zerphyis.ZeCodesBackEnd.infra.persistance.repository;

import dev.Zerphyis.ZeCodesBackEnd.infra.persistance.entity.PartnerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PartnerRepositoryJpa extends JpaRepository<PartnerJpaEntity, UUID> {

    Optional<PartnerJpaEntity> findByDocument(String document);

    List<PartnerJpaEntity> findByActive(boolean active);

    @Query("""
        SELECT p
        FROM PartnerJpaEntity p
        WHERE p.active = true
          AND within(:point, p.coverageArea) = true
        ORDER BY distance(p.address, :point)
        """)
    Optional<PartnerJpaEntity> searchNearest(
            @Param("point") org.locationtech.jts.geom.Point point
    );
}
