package dev.Zerphyis.ZeCodesBackEnd.model.entites;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.vo.GeoJsonMultiPolygon;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.vo.GeoJsonPoint;

import java.util.Objects;
import java.util.UUID;

public class Partner {
    private final UUID id;
    private final String tradingName;
    private final String ownerName;
    private final String document;
    private final GeoJsonMultiPolygon coverageArea;
    private final GeoJsonPoint address;
    private final boolean active;


    public Partner(
            UUID id,
            String tradingName,
            String ownerName,
            String document,
            GeoJsonMultiPolygon coverageArea,
            GeoJsonPoint address,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "Id é obrigatório");
        this.tradingName = validateString(tradingName, "TradingName");
        this.ownerName = validateString(ownerName, "OwnerName");
        this.document = validateString(document, "Document");
        this.coverageArea = Objects.requireNonNull(coverageArea, "CoverageArea é obrigatória");
        this.address = Objects.requireNonNull(address, "Address é obrigatório");
        this.active = active;
    }


    private String validateString(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " não pode ser vazio");
        }
        return value;
    }


    public UUID getId() {
        return id;
    }


    public String getTradingName() {
        return tradingName;
    }


    public String getOwnerName() {
        return ownerName;
    }


    public String getDocument() {
        return document;
    }


    public GeoJsonMultiPolygon getCoverageArea() {
        return coverageArea;
    }


    public GeoJsonPoint getAddress() {
        return address;
    }


    public boolean isActive() {
        return active;
    }


    public Partner activate() {
        return new Partner(id, tradingName, ownerName, document, coverageArea, address, true);
    }


    public Partner deactivate() {
        return new Partner(id, tradingName, ownerName, document, coverageArea, address, false);
    }
}
