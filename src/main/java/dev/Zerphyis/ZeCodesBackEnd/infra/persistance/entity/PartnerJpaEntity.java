package dev.Zerphyis.ZeCodesBackEnd.infra.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

@Table(name = "partners")
@Entity
public class PartnerJpaEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;


    @Column(name = "trading_name", nullable = false)
    private String tradingName;


    @Column(name = "owner_name", nullable = false)
    private String ownerName;


    @Column(nullable = false, unique = true)
    private String document;


    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "coverage_area", columnDefinition = "MULTIPOLYGON", nullable = false)
    private MultiPolygon coverageArea;


    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "address", columnDefinition = "POINT", nullable = false)
    private Point address;


    @Column(nullable = false)
    private boolean active;


    protected PartnerJpaEntity() {
    }


    public PartnerJpaEntity(
            UUID id,
            String tradingName,
            String ownerName,
            String document,
            MultiPolygon coverageArea,
            Point address,
            boolean active
    ) {
        this.id = id;
        this.tradingName = tradingName;
        this.ownerName = ownerName;
        this.document = document;
        this.coverageArea = coverageArea;
        this.address = address;
        this.active = active;
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


    public MultiPolygon getCoverageArea() {
        return coverageArea;
    }


    public Point getAddress() {
        return address;
    }


    public boolean isActive() {
        return active;
    }
}
