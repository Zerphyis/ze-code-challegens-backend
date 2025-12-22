package dev.Zerphyis.ZeCodesBackEnd.aplication.service.interfaceService;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;

import java.util.List;

public interface ServicePartner {

    Partner createPartner(Partner partner);

    Partner getPartnerById(String id);

    List<Partner> getAllPartners();

    List<Partner> getActivePartners();

    List<Partner> getInactivePartners();

    Partner searchNearestPartner(Double latitude, Double longitude);

    void deactivatePartner(String id);

    void activatePartner(String id);
}
