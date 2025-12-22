package dev.Zerphyis.ZeCodesBackEnd.infra.config;

import dev.Zerphyis.ZeCodesBackEnd.model.repository.PartnerRepositoryGateway;
import dev.Zerphyis.ZeCodesBackEnd.model.useCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PartnerUseCaseConfig {

    @Bean
    public CreatePartnerUseCaseImpl createPartnerUseCase(PartnerRepositoryGateway gateway) {
        return new CreatePartnerUseCaseImpl(gateway);
    }

    @Bean
    public GetPartnerFindByIdUseCaseImpl getPartnerByIdUseCase(PartnerRepositoryGateway gateway) {
        return new GetPartnerFindByIdUseCaseImpl(gateway);
    }

    @Bean
    public SearchPartnerUseCaseImpl searchPartnerUseCase(PartnerRepositoryGateway gateway) {
        return new SearchPartnerUseCaseImpl(gateway);
    }

    @Bean
    public GetAllPartnerUseCaseImpl getAllPartnerUseCase(PartnerRepositoryGateway gateway) {
        return new GetAllPartnerUseCaseImpl(gateway);
    }

    @Bean
    public GetActivePartnersUseCaseImpl getActivePartnersUseCase(PartnerRepositoryGateway gateway) {
        return new GetActivePartnersUseCaseImpl(gateway);
    }

    @Bean
    public GetInactivePartnersUseCaseImpl getInactivePartnersUseCase(PartnerRepositoryGateway gateway) {
        return new GetInactivePartnersUseCaseImpl(gateway);
    }

    @Bean
    public DeactivePartnerUseCaseImpl deactivePartnerUseCase(PartnerRepositoryGateway gateway) {
        return new DeactivePartnerUseCaseImpl(gateway);
    }

    @Bean
    public ActivePartnerUseCaseImpl activePartnerUseCase(PartnerRepositoryGateway gateway) {
        return new ActivePartnerUseCaseImpl(gateway);
    }
}
