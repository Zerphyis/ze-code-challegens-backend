package dev.Zerphyis.ZeCodesBackEnd.infra.persistance.mapper;

import dev.Zerphyis.ZeCodesBackEnd.infra.persistance.entity.PartnerJpaEntity;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.vo.GeoJsonMultiPolygon;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.vo.GeoJsonPoint;
import org.locationtech.jts.geom.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartnerMapperEntitys {

    private static final int SRID = 4326;


    private final GeometryFactory geometryFactory =
            new GeometryFactory(new PrecisionModel(), SRID);


    public PartnerJpaEntity toJpaEntity(Partner partner) {
        return new PartnerJpaEntity(
                partner.getId(),
                partner.getTradingName(),
                partner.getOwnerName(),
                partner.getDocument(),
                toMultiPolygon(partner.getCoverageArea()),
                toPoint(partner.getAddress()),
                partner.isActive()
        );
    }


    public Partner toDomain(PartnerJpaEntity entity) {
        return new Partner(
                entity.getId(),
                entity.getTradingName(),
                entity.getOwnerName(),
                entity.getDocument(),
                toGeoJsonMultiPolygon(entity.getCoverageArea()),
                toGeoJsonPoint(entity.getAddress()),
                entity.isActive()
        );
    }


    private Point toPoint(GeoJsonPoint geoPoint) {
        Point point = geometryFactory.createPoint(
                new Coordinate(geoPoint.getLongitude(), geoPoint.getLatitude())
        );
        point.setSRID(SRID);
        return point;
    }


    private MultiPolygon toMultiPolygon(GeoJsonMultiPolygon geoMultiPolygon) {
        Polygon[] polygons = geoMultiPolygon.getCoordinates().stream()
                .map(this::toPolygon)
                .toArray(Polygon[]::new);


        MultiPolygon mp = geometryFactory.createMultiPolygon(polygons);
        mp.setSRID(SRID);
        return mp;
    }


    private Polygon toPolygon(List<List<GeoJsonPoint>> polygon) {
        LinearRing shell = toLinearRing(polygon.get(0));


        LinearRing[] holes = polygon.size() > 1
                ? polygon.subList(1, polygon.size()).stream()
                .map(this::toLinearRing)
                .toArray(LinearRing[]::new)
                : null;


        return geometryFactory.createPolygon(shell, holes);
    }


    private LinearRing toLinearRing(List<GeoJsonPoint> points) {
        Coordinate[] coordinates = points.stream()
                .map(p -> new Coordinate(p.getLongitude(), p.getLatitude()))
                .toArray(Coordinate[]::new);


        return geometryFactory.createLinearRing(coordinates);
    }


    private GeoJsonPoint toGeoJsonPoint(Point point) {
        return new GeoJsonPoint(point.getX(), point.getY());
    }


    private GeoJsonMultiPolygon toGeoJsonMultiPolygon(MultiPolygon multiPolygon) {
        List<List<List<GeoJsonPoint>>> polygons =
                java.util.stream.IntStream.range(0, multiPolygon.getNumGeometries())
                        .mapToObj(i -> (Polygon) multiPolygon.getGeometryN(i))
                        .map(this::polygonToGeoJson)
                        .toList();


        return new GeoJsonMultiPolygon(polygons);
    }


    private List<List<GeoJsonPoint>> polygonToGeoJson(Polygon polygon) {
        List<List<GeoJsonPoint>> rings = new java.util.ArrayList<>();


        rings.add(coordinatesToPoints(polygon.getExteriorRing().getCoordinates()));


        for (int i = 0; i < polygon.getNumInteriorRing(); i++) {
            rings.add(coordinatesToPoints(polygon.getInteriorRingN(i).getCoordinates()));
        }


        return rings;
    }


    private List<GeoJsonPoint> coordinatesToPoints(Coordinate[] coordinates) {
        return java.util.Arrays.stream(coordinates)
                .map(c -> new GeoJsonPoint(c.x, c.y))
                .toList();
    }
}
