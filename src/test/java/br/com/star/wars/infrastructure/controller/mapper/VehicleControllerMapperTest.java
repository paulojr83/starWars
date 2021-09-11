package br.com.star.wars.infrastructure.controller.mapper;

import br.com.star.wars.core.vehicle.io.VehicleBoundaryOutput;
import br.com.star.wars.infrastructure.controller.request.VehicleRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Vehicle Controller Mapper Tests")
public class VehicleControllerMapperTest {

    @Test
    @DisplayName("Deve verificar o mapeamento do VehicleBoundaryInput.")
    public void shouldVerifyTheVehicleBoundaryInputMap() {
        final var request = VehicleRequest.builder().id(4).build();
        final var response = VehicleControllerMapper.convertTo(request);

        Assertions.assertEquals(4, response.getId());
    }

    @Test
    @DisplayName("Deve verificar o mapeamento do VehicleResponse.")
    public void shouldVerifyTheVehicleResponseMap() {
        final var request = VehicleBoundaryOutput.builder()
                .cargoCapacity("3000").build();
        final var response = VehicleControllerMapper.convertTo(request);

        Assertions.assertEquals("3000", response.getCargoCapacity());
    }
}
