package dev.Zerphyis.ZeCodesBackEnd.model.useCases;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.PartnerNotFoundException;
import dev.Zerphyis.ZeCodesBackEnd.model.repository.PartnerRepositoryGateway;
import dev.Zerphyis.ZeCodesBackEnd.tests.utils.PartnerTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeactivePartnerUseCaseImplTest {


    @Mock
    PartnerRepositoryGateway gateway;

    @InjectMocks
    DeactivePartnerUseCaseImpl useCase;

    @Test
    void happyPathDeactivatesPartner() {
        Partner partner = PartnerTestFactory.activePartner();

        when(gateway.findById(partner.getId()))
                .thenReturn(Optional.of(partner));

        useCase.execute(partner.getId());

        assertFalse(partner.isActive());
        verify(gateway).save(partner);
    }


    @Test
    void
    sadpartnerNotFound() {
        UUID id = UUID.randomUUID();

        when(gateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                PartnerNotFoundException.class,
                () -> useCase.execute(id)
        );
    }
}


