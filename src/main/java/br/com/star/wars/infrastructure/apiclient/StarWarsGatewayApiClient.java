package br.com.star.wars.infrastructure.apiclient;

import br.com.star.wars.core.vehicle.VehicleApiClientInput;
import br.com.star.wars.core.vehicle.VehicleApiClientOutput;
import br.com.star.wars.core.vehicle.VehicleGatewayApiClient;
import br.com.star.wars.core.vehicles.VehiclesApiClientInput;
import br.com.star.wars.core.vehicles.VehiclesApiClientOutput;
import br.com.star.wars.infrastructure.apiclient.feign.StarWarsFeign;
import br.com.star.wars.infrastructure.apiclient.mapper.StarWarsApiClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StarWarsGatewayApiClient implements VehicleGatewayApiClient {

    private final StarWarsFeign feign;

    @Override
    public VehicleApiClientOutput getVehicle(final VehicleApiClientInput input) {
        log.info("M=getVehicle, executando endpoint vehicle do StarWars.");
        final var response = feign.getVehicle(input.getId());
        return StarWarsApiClientMapper.convertTo(response);
    }

    @Override
    public VehiclesApiClientOutput getVehicles(final VehiclesApiClientInput input) {
        log.info("M=getVehicles, executando endpoint vehicles do StarWars.");
        final var response = feign.getVehicles(input.getPage());
        return StarWarsApiClientMapper.convertTo(response);
    }
}
