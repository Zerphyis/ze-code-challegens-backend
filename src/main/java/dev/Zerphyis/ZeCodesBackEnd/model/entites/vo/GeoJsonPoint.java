package dev.Zerphyis.ZeCodesBackEnd.model.entites.vo;

import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.InvalidGeoDataException;

import java.util.Objects;

public class GeoJsonPoint {

    private final Double longitude;
    private final Double  latitude;

    public GeoJsonPoint(Double longitude, Double latitude) {
        if(longitude < -100 || longitude > 100){
            throw new InvalidGeoDataException("Longitude Inválida");
        }
        if(latitude < -90 || latitude > 90){
            throw new InvalidGeoDataException("Latitude Inválida");
        }
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoJsonPoint that)) return false;
        return Double.compare(that.longitude, longitude) == 0 &&
                Double.compare(that.latitude, latitude) == 0;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
