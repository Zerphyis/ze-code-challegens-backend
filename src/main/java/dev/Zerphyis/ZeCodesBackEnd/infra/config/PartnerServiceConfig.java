package dev.Zerphyis.ZeCodesBackEnd.infra.config;

import dev.Zerphyis.ZeCodesBackEnd.aplication.service.PartnerServiceImpl;
import dev.Zerphyis.ZeCodesBackEnd.model.useCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PartnerServiceConfig {
    @Bean
    public PartnerServiceImpl partnerService(
            CreatePartnerUseCaseImpl createPartnerUseCase,
            GetPartnerFindByIdUseCaseImpl getPartnerByIdUseCase,
            SearchPartnerUseCaseImpl searchPartnerUseCase,
            GetAllPartnerUseCaseImpl getAllPartnerUseCase,
            GetActivePartnersUseCaseImpl getActivePartnersUseCase,
            GetInactivePartnersUseCaseImpl getInactivePartnersUseCase,
            DeactivePartnerUseCaseImpl deactivePartnerUseCase,
            ActivePartnerUseCaseImpl activePartnerUseCase
    ) {
        return new PartnerServiceImpl(
                createPartnerUseCase,
                getPartnerByIdUseCase,
                searchPartnerUseCase,
                getAllPartnerUseCase,
                getActivePartnersUseCase,
                getInactivePartnersUseCase,
                deactivePartnerUseCase,
                activePartnerUseCase
        );
    }
}
