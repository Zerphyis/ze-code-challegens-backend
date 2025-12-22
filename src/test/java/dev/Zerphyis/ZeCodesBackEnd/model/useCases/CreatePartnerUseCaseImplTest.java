import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.vo.GeoJsonMultiPolygon;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.vo.GeoJsonPoint;
import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.PartnerAlreadyExistsException;
import dev.Zerphyis.ZeCodesBackEnd.model.repository.PartnerRepositoryGateway;
import dev.Zerphyis.ZeCodesBackEnd.model.useCases.CreatePartnerUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePartnerUseCaseImplTest {

    @Mock
    private PartnerRepositoryGateway repositoryGateway;

    @Mock
    private GeoJsonMultiPolygon mockCoverageArea;

    @Mock
    private GeoJsonPoint mockAddress;

    @InjectMocks
    private CreatePartnerUseCaseImpl useCase;

    private Partner partner;

    @BeforeEach
    void setUp() {
        partner = new Partner(
                UUID.randomUUID(),
                "Zé Codes Ltda",
                "Zerphyis",
                "123456789/0001",
                mockCoverageArea,
                mockAddress,
                true
        );
    }

    @Test
    void happyPathCreatePartner() {
        when(repositoryGateway.findById(any(UUID.class))).thenReturn(Optional.empty());
        when(repositoryGateway.findByDocument(anyString())).thenReturn(Optional.empty());

        when(repositoryGateway.save(any(Partner.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Partner result = useCase.execute(partner);

        assertNotNull(result);
        assertEquals("Zé Codes Ltda", result.getTradingName());
        assertEquals("123456789/0001", result.getDocument());

        verify(repositoryGateway).save(partner);
    }

    @Test
    void sadPathPartnerAlreadyExistsByDocument() {
        when(repositoryGateway.findById(partner.getId())).thenReturn(Optional.empty());
        when(repositoryGateway.findByDocument(partner.getDocument()))
                .thenReturn(Optional.of(partner));

        assertThrows(
                PartnerAlreadyExistsException.class,
                () -> useCase.execute(partner)
        );

        verify(repositoryGateway, never()).save(any());
    }
}