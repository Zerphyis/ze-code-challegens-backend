package dev.Zerphyis.ZeCodesBackEnd.model.entites.vo;

import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.InvalidGeoDataException;

import java.util.List;
import java.util.Objects;

public class GeoJsonMultiPolygon {
    private final List<List<List<GeoJsonPoint>>> coordinates;

    public GeoJsonMultiPolygon(List<List<List<GeoJsonPoint>>> coordinates) {
        if(coordinates == null || coordinates.isEmpty()){
            throw new InvalidGeoDataException("MultiPolygon não pode ser vazio");
        }
        this.coordinates = List.copyOf(coordinates);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoJsonMultiPolygon that)) return false;
        return Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    public List<List<List<GeoJsonPoint>>> getCoordinates() {
        return coordinates;
    }
}
