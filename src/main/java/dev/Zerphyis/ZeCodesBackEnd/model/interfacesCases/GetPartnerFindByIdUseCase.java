package dev.Zerphyis.ZeCodesBackEnd.model.interfacesCases;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;

import java.util.UUID;

public interface GetPartnerFindByIdUseCase {
     Partner execute(UUID id);
}
