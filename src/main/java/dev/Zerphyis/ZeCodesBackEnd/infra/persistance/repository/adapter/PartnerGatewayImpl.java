package dev.Zerphyis.ZeCodesBackEnd.infra.persistance.repository.adapter;

import dev.Zerphyis.ZeCodesBackEnd.infra.persistance.mapper.PartnerMapperEntitys;
import dev.Zerphyis.ZeCodesBackEnd.infra.persistance.repository.PartnerRepositoryJpa;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.repository.PartnerRepositoryGateway;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Repository
public class PartnerGatewayImpl implements PartnerRepositoryGateway {
   private static final int SRID =4326;
    private PartnerRepositoryJpa repositoryJpa;
    private final PartnerMapperEntitys mapper;
    private final GeometryFactory geometryFactory =
            new GeometryFactory(new PrecisionModel(), SRID);


    public PartnerGatewayImpl(PartnerMapperEntitys mapper, PartnerRepositoryJpa repositoryJpa) {
        this.mapper = mapper;
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Optional<Partner> findById(UUID id) {
        return repositoryJpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Partner> findByDocument(String document) {
        return repositoryJpa.findByDocument(document).map(mapper::toDomain);
    }


    @Override
    public Partner save(Partner partner) {
        var saved = repositoryJpa.save(
                mapper.toJpaEntity(partner)
        );
        return mapper.toDomain(saved);
    }


    @Override
    public Optional<Partner> searchNearest(Double latitude, Double longitude) {
        Point point = geometryFactory.createPoint(
                new Coordinate(longitude, latitude)
        );
        point.setSRID(SRID);

        return repositoryJpa.searchNearest(point)
                .map(mapper::toDomain);
    }


    @Override
    public void delete(UUID id) {
        repositoryJpa.deleteById(id);
    }
    @Override
    public List<Partner> findAll() {
        return repositoryJpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Partner> findByActive(boolean active) {
        return repositoryJpa.findByActive(active)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}
