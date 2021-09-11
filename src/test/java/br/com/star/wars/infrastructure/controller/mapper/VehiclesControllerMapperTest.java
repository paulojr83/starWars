package br.com.star.wars.infrastructure.controller.mapper;

import br.com.star.wars.core.vehicle.VehicleApiClientOutput;
import br.com.star.wars.core.vehicles.io.VehiclesBoundaryOutput;
import br.com.star.wars.infrastructure.apiclient.mapper.StarWarsApiClientMapper;
import br.com.star.wars.infrastructure.apiclient.response.StarWarsVehicleApiClientResponse;
import br.com.star.wars.infrastructure.apiclient.response.StarWarsVehiclesApiClientResponse;
import br.com.star.wars.infrastructure.controller.request.VehiclesRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@DisplayName("Vehicles Controller Mapper Tests")
public class VehiclesControllerMapperTest {

    @Test
    @DisplayName("Deve verificar o mapeamento do VehiclesBoundaryInput.")
    public void shouldVerifyTheVehiclesBoundaryInputMap() {
        final var request = VehiclesRequest.builder().page(4).build();
        final var response = VehiclesControllerMapper.convertTo(request);

        Assertions.assertEquals(Integer.valueOf("4"), response.getPage());
    }

    @Test
    @DisplayName("Deve verificar o mapeamento do VehiclesResponse.")
    public void shouldVerifyTheVehiclesResponseMap() {
        final var request = VehiclesBoundaryOutput.builder()
                .count(3).build();
        final var response = VehiclesControllerMapper.convertTo(request);

        Assertions.assertEquals(Integer.valueOf(3), response.getCount());
    }

    @Test
    @DisplayName("Deve verificar o mapeamento do VehiclesResponse com result preenchido.")
    public void shouldVerifyTheVehiclesResponseMapWithResultFilled() {
        final var request = VehiclesBoundaryOutput.builder()
                .results(Arrays.asList(VehicleApiClientOutput.builder()
                        .build()))
                .count(3).build();
        final var response = VehiclesControllerMapper.convertTo(request);

        Assertions.assertEquals(Integer.valueOf(3), response.getCount());
    }

    @Test
    @DisplayName("Deve verificar o mapeamento do VehicleApiClientOutput.")
    public void shouldVerifyTheVehicleApiClientOutputMap() {
        final var response = StarWarsVehiclesApiClientResponse
                .builder()
                .results(Arrays.asList(StarWarsVehicleApiClientResponse.builder()
                        .build()))
                .count(2).build();
        final var apiClientOutput = StarWarsApiClientMapper.convertTo(response);

        Assertions.assertFalse(apiClientOutput.getResults().isEmpty());
    }
}
