package dev.Zerphyis.ZeCodesBackEnd.model.useCases;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.repository.PartnerRepositoryGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetActivePartnersUseCaseImplTest {
        @Mock
    PartnerRepositoryGateway gateway;

        @InjectMocks
        GetActivePartnersUseCaseImpl useCase;

        @Test
        void happypathassetsearch() {
            when(gateway.findByActive(true)).thenReturn(List.of());

            List<Partner> result = useCase.execute();

            assertNotNull(result);
        }
    }


