package dev.Zerphyis.ZeCodesBackEnd.aplication.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PartnerRequestDto(
        @NotBlank
        String tradingName,

        @NotBlank
        String ownerName,

        @NotBlank
        String document,

        @NotNull
        Double latitude,

        @NotNull
        Double longitude) {
}
