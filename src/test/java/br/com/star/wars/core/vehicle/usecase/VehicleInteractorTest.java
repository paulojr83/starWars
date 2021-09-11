package br.com.star.wars.core.vehicle.usecase;

import br.com.star.wars.core.vehicle.VehicleBoundary;
import br.com.star.wars.core.vehicle.io.VehicleBoundaryInput;
import br.com.star.wars.infrastructure.apiclient.StarWarsGatewayApiClient;
import br.com.star.wars.infrastructure.apiclient.feign.StarWarsFeign;
import br.com.star.wars.infrastructure.apiclient.response.StarWarsVehicleApiClientResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Vehicle Tests")
public class VehicleInteractorTest {

    private VehicleBoundary boundary;

    @Mock
    private StarWarsFeign feign;

    @BeforeEach
    public void setUp() {
        final var gatewayApiClient = new StarWarsGatewayApiClient(feign);
        boundary = new VehicleInteractor(gatewayApiClient);
    }

    @Test
    @DisplayName("Deve verificar Vehicle.")
    public void shouldVerifyVehicle() throws Exception {
        final var input = VehicleBoundaryInput.builder().id(Integer.valueOf(2)).build();
        final var mockResponse = StarWarsVehicleApiClientResponse
                .builder().cargoCapacity("2000").build();
        Mockito.when(feign.getVehicle(Mockito.any())).thenReturn(mockResponse);
        final var outputBoundary = boundary.execute(input);
        Assertions.assertEquals("2000", outputBoundary.getCargoCapacity());
    }

    @Test
    @DisplayName("Deve levantar exceção quando Vehicle capacity for menor do que 2.")
    public void shouldThrowExceptionWhenVehicleCapacityIsLessThan2() throws Exception {
        final var input = VehicleBoundaryInput.builder().id(Integer.valueOf(2)).build();
        final var mockResponse = StarWarsVehicleApiClientResponse
                .builder().cargoCapacity("1").build();
        Mockito.when(feign.getVehicle(Mockito.any())).thenReturn(mockResponse);
        final var erroMessage = Assertions.assertThrows(Exception.class, () -> {
            boundary.execute(input);
        });

        Assertions.assertEquals("Capacidade do veiculo invalida!", erroMessage.getMessage());
    }
}
