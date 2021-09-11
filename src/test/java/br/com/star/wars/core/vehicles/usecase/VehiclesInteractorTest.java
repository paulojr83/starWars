package br.com.star.wars.core.vehicles.usecase;

import br.com.star.wars.core.vehicles.VehiclesBoundary;
import br.com.star.wars.core.vehicles.io.VehiclesBoundaryInput;
import br.com.star.wars.infrastructure.apiclient.StarWarsGatewayApiClient;
import br.com.star.wars.infrastructure.apiclient.feign.StarWarsFeign;
import br.com.star.wars.infrastructure.apiclient.response.StarWarsVehicleApiClientResponse;
import br.com.star.wars.infrastructure.apiclient.response.StarWarsVehiclesApiClientResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
@DisplayName("Vehicles Tests")
public class VehiclesInteractorTest {

    private VehiclesBoundary boundary;

    @Mock
    private StarWarsFeign feign;

    @BeforeEach
    public void setUp() {
        final var gatewayApiClient = new StarWarsGatewayApiClient(feign);
        boundary = new VehiclesInteractor(gatewayApiClient);
    }

    @Test
    @DisplayName("Deve verificar Vehicles.")
    public void shouldVerifyVehicles() throws Exception {
        final var input = VehiclesBoundaryInput.builder().page(Integer.valueOf(2)).build();
        final var mockResponse = StarWarsVehiclesApiClientResponse
                .builder()
                .results(Arrays.asList(StarWarsVehicleApiClientResponse.builder().build()))
                .count(2).build();
        Mockito.when(feign.getVehicles(Mockito.any())).thenReturn(mockResponse);
        final var outputBoundary = boundary.execute(input);
        Assertions.assertEquals(Integer.valueOf(2), outputBoundary.getCount());
    }

    @Test
    @DisplayName("Deve levantar exceção quando Vehicle result for vazio.")
    public void shouldThrowExceptionWhenResultIsEmpty() throws Exception {
        final var input = VehiclesBoundaryInput.builder().page(Integer.valueOf(2)).build();
        final var mockResponse = StarWarsVehiclesApiClientResponse
                .builder().build();
        Mockito.when(feign.getVehicles(Mockito.any())).thenReturn(mockResponse);
        final var erroMessage = Assertions.assertThrows(Exception.class, () -> {
            boundary.execute(input);
        });

        Assertions.assertEquals("Resultado inválido!", erroMessage.getMessage());
    }

    @Test
    @DisplayName("Deve setar valor de default quando o page for nulo.")
    public void shouldSetDefaulValueWhenPageIsNull() throws Exception {
        final var input = VehiclesBoundaryInput.builder().build();
        final var mockResponse = StarWarsVehiclesApiClientResponse
                .builder()
                .results(Arrays.asList(StarWarsVehicleApiClientResponse.builder().build()))
                .count(2).build();
        Mockito.when(feign.getVehicles(Mockito.any())).thenReturn(mockResponse);
        final var outputBoundary = boundary.execute(input);

        Assertions.assertEquals(Integer.valueOf(2), outputBoundary.getCount());
    }
}
