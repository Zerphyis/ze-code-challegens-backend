package dev.Zerphyis.ZeCodesBackEnd.model.useCases;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.DomainException;
import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.NoPartnerAvailableException;
import dev.Zerphyis.ZeCodesBackEnd.model.interfacesCases.SearchPartnerUseCase;
import dev.Zerphyis.ZeCodesBackEnd.model.repository.PartnerRepositoryGateway;

public class SearchPartnerUseCaseImpl implements SearchPartnerUseCase {

    private final PartnerRepositoryGateway repositoryGateway;

    public SearchPartnerUseCaseImpl(PartnerRepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public Partner execute(Double latitude, Double longitude) {
            validateCoordinates(latitude, longitude);

            return repositoryGateway
                    .searchNearest(latitude, longitude)
                    .orElseThrow(() ->
                            new NoPartnerAvailableException()
                    );
        }

        private void validateCoordinates(Double latitude, Double longitude) {
            if (latitude == null || longitude == null) {
                throw new DomainException("Latitude e longitude são obrigatórias");
            }

            if (latitude < -90 || latitude > 90) {
                throw new DomainException("Latitude fora do intervalo válido");
            }

            if (longitude < -180 || longitude > 180) {
                throw new DomainException("Longitude fora do intervalo válido");
            }
    }
}
