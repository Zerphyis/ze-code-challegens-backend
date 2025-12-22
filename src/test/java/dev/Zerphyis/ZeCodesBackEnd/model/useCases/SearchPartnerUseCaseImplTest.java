package dev.Zerphyis.ZeCodesBackEnd.model.useCases;

import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.DomainException;
import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.NoPartnerAvailableException;
import dev.Zerphyis.ZeCodesBackEnd.model.repository.PartnerRepositoryGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SearchPartnerUseCaseImplTest {

    @Mock
    private PartnerRepositoryGateway gateway;

    @InjectMocks
    private SearchPartnerUseCaseImpl useCase;


    @Test
    void happyPathPartnerFound() {

        Partner partner = mock(Partner.class);

        when(gateway.searchNearest(anyDouble(), anyDouble()))
                .thenReturn(Optional.of(partner));

        Partner result = useCase.execute(-23.5, -46.6);

        assertNotNull(result);

        verify(gateway).searchNearest(-23.5, -46.6);
    }


    @Test
    void sadPathInvalidCoordinates() {

        assertThrows(
                DomainException.class,
                () -> useCase.execute(null, null)
        );

        verifyNoInteractions(gateway);
    }

    @Test
    void sadPathNoPartnerAvailable() {

        when(gateway.searchNearest(anyDouble(), anyDouble()))
                .thenReturn(Optional.empty());

        assertThrows(
                NoPartnerAvailableException.class,
                () -> useCase.execute(-23.5, -46.6)
        );

        verify(gateway).searchNearest(-23.5, -46.6);
    }
}
