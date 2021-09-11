package br.com.star.wars.core.vehicle.usecase;

import br.com.star.wars.core.vehicle.VehicleApiClientInput;
import br.com.star.wars.core.vehicle.VehicleApiClientOutput;
import br.com.star.wars.core.vehicle.VehicleBoundary;
import br.com.star.wars.core.vehicle.VehicleGatewayApiClient;
import br.com.star.wars.core.vehicle.entity.VehicleEntity;
import br.com.star.wars.core.vehicle.io.VehicleBoundaryInput;
import br.com.star.wars.core.vehicle.io.VehicleBoundaryOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehicleInteractor implements VehicleBoundary {

    private final VehicleGatewayApiClient apiClient;

    @Override
    public VehicleBoundaryOutput execute(final VehicleBoundaryInput input) throws Exception {
        log.info("M=execute, executando VehicleInteractor input.getId()={}", input.getId());

        final var apiClientOutput = apiClient.getVehicle(VehicleApiClientInput.builder()
                .id(input.getId()).build());

        if (!VehicleEntity.isCapacityValid(Integer.valueOf(apiClientOutput.getCargoCapacity()))) {
            throw new Exception("Capacidade do veiculo invalida!");
        }

        return getVehicleBoundaryOutput(apiClientOutput);
    }

    private VehicleBoundaryOutput getVehicleBoundaryOutput(final VehicleApiClientOutput apiClientOutput) {
        return VehicleBoundaryOutput
                .builder()
                .name(apiClientOutput.getName())
                .cargoCapacity(apiClientOutput.getCargoCapacity())
                .consumables(apiClientOutput.getConsumables())
                .costInCredits(apiClientOutput.getCostInCredits())
                .created(apiClientOutput.getCreated())
                .crew(apiClientOutput.getCrew())
                .edited(apiClientOutput.getEdited())
                .films(apiClientOutput.getFilms())
                .length(apiClientOutput.getLength())
                .manufacturer(apiClientOutput.getManufacturer())
                .maxAtmospheringSpeed(apiClientOutput.getMaxAtmospheringSpeed())
                .model(apiClientOutput.getModel())
                .passengers(apiClientOutput.getPassengers())
                .pilots(apiClientOutput.getPilots())
                .url(apiClientOutput.getUrl())
                .vehicleClass(apiClientOutput.getVehicleClass())
                .build();
    }
}
