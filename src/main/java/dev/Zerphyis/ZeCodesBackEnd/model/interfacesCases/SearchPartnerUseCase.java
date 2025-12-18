package dev.Zerphyis.ZeCodesBackEnd.model.interfacesCases;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;

public interface SearchPartnerUseCase {
    Partner execute(Double latitude, Double longitude);
}
