package dev.Zerphyis.ZeCodesBackEnd.aplication.dtos;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.vo.GeoJsonMultiPolygon;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.vo.GeoJsonPoint;

import java.util.List;
import java.util.UUID;

public class RequetsMapper {

    public static Partner toEntity(PartnerRequestDto dto) {

        GeoJsonPoint address = new GeoJsonPoint(
                dto.longitude(),
                dto.latitude()
        );

        GeoJsonMultiPolygon coverageArea = createMinimalCoverageArea(address);

        return new Partner(
                UUID.randomUUID(),
                dto.tradingName(),
                dto.ownerName(),
                dto.document(),
                coverageArea,
                address,
                true
        );
    }


    public static PartnerResponseDto toResponse(Partner partner) {
        return new PartnerResponseDto(
                partner.getId(),
                partner.getTradingName(),
                partner.getOwnerName(),
                partner.getDocument(),
                partner.isActive(),
                partner.getAddress().getLatitude(),
                partner.getAddress().getLongitude()
        );
    }


    private static GeoJsonMultiPolygon createMinimalCoverageArea(GeoJsonPoint point) {

        double lng = point.getLongitude();
        double lat = point.getLatitude();

        double delta = 0.01;

        List<GeoJsonPoint> ring = List.of(
                new GeoJsonPoint(lng - delta, lat - delta),
                new GeoJsonPoint(lng + delta, lat - delta),
                new GeoJsonPoint(lng + delta, lat + delta),
                new GeoJsonPoint(lng - delta, lat + delta),
                new GeoJsonPoint(lng - delta, lat - delta)
        );

        List<List<GeoJsonPoint>> polygon = List.of(ring);
        List<List<List<GeoJsonPoint>>> multiPolygon = List.of(polygon);

        return new GeoJsonMultiPolygon(multiPolygon);
    }
}
