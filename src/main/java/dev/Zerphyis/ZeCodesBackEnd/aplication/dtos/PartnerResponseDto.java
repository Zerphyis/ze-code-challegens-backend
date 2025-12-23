package dev.Zerphyis.ZeCodesBackEnd.aplication.dtos;

import java.util.UUID;

public record PartnerResponseDto(UUID id,
                                 String tradingName,
                                 String ownerName,
                                 String document,
                                 boolean active,
                                 Double latitude,
                                 Double longitude) {
}
