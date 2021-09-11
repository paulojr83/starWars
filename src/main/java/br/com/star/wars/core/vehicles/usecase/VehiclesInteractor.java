package br.com.star.wars.core.vehicles.usecase;

import br.com.star.wars.core.vehicle.VehicleGatewayApiClient;
import br.com.star.wars.core.vehicles.VehiclesApiClientInput;
import br.com.star.wars.core.vehicles.VehiclesBoundary;
import br.com.star.wars.core.vehicles.entity.VehiclesEntity;
import br.com.star.wars.core.vehicles.io.VehiclesBoundaryInput;
import br.com.star.wars.core.vehicles.io.VehiclesBoundaryOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehiclesInteractor implements VehiclesBoundary {

    private final VehicleGatewayApiClient apiClient;

    @Override
    public VehiclesBoundaryOutput execute(final VehiclesBoundaryInput input) throws Exception {
        log.info("M=execute, executando VehiclesInteractor input.getPage={}", input.getPage());

        final var page = VehiclesEntity.setDefaultPage(input);

        final var outputApiClient = apiClient.getVehicles(VehiclesApiClientInput
                .builder().page(page.getPage()).build());

        if (!VehiclesEntity.isValidResult(outputApiClient.getResults())) {
            throw new Exception("Resultado inválido!");
        }

        return VehiclesBoundaryOutput
                .builder()
                .count(outputApiClient.getCount())
                .next(outputApiClient.getNext())
                .previous(outputApiClient.getPrevious())
                .results(outputApiClient.getResults())
                .build();
    }
}
