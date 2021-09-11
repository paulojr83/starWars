package br.com.star.wars.infrastructure.apiclient.mapper;

import br.com.star.wars.core.vehicle.VehicleApiClientOutput;
import br.com.star.wars.core.vehicles.VehiclesApiClientOutput;
import br.com.star.wars.infrastructure.apiclient.response.StarWarsVehicleApiClientResponse;
import br.com.star.wars.infrastructure.apiclient.response.StarWarsVehiclesApiClientResponse;
import br.com.star.wars.infrastructure.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;

public final class StarWarsApiClientMapper {

    private StarWarsApiClientMapper() {

    }

    public static VehicleApiClientOutput convertTo(final StarWarsVehicleApiClientResponse input) {
        return MapperUtils.map(input, VehicleApiClientOutput.class);
    }

    public static VehiclesApiClientOutput convertTo(final StarWarsVehiclesApiClientResponse input) {

        final List<VehicleApiClientOutput> vehicleResponse = new ArrayList<>();

        if (input.getResults() != null) {
            input.getResults().forEach(vehicleApiClientResponse -> {
                vehicleResponse.add(MapperUtils.map(vehicleApiClientResponse, VehicleApiClientOutput.class));
            });
        }

        return MapperUtils.map(input, VehiclesApiClientOutput.class, result -> {
            result.setResults(vehicleResponse);
        });
    }
}
