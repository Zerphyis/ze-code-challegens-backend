package dev.Zerphyis.ZeCodesBackEnd.tests.utils;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.vo.GeoJsonMultiPolygon;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.vo.GeoJsonPoint;

import java.util.List;
import java.util.UUID;

public class PartnerTestFactory {
    public static Partner activePartner() {
        return new Partner(
                UUID.randomUUID(),
                "Zé Codes",
                "Zé",
                "12345678901",
                new GeoJsonMultiPolygon(
                        List.of(
                                List.of(
                                        List.of(
                                                new GeoJsonPoint(-46.0, -23.0),
                                                new GeoJsonPoint(-46.1, -23.1),
                                                new GeoJsonPoint(-46.2, -23.2),
                                                new GeoJsonPoint(-46.0, -23.0)
                                        )
                                )
                        )
                ),
                new GeoJsonPoint(-46.6, -23.5),
                true
        );
    }

    public static Partner inactivePartner() {
        Partner p = activePartner();
        p.deactivate();
        return p;
    }
}
