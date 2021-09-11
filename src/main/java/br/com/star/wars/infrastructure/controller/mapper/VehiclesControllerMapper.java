package br.com.star.wars.infrastructure.controller.mapper;

import br.com.star.wars.core.vehicles.io.VehiclesBoundaryInput;
import br.com.star.wars.core.vehicles.io.VehiclesBoundaryOutput;
import br.com.star.wars.infrastructure.controller.request.VehiclesRequest;
import br.com.star.wars.infrastructure.controller.response.VehicleResponse;
import br.com.star.wars.infrastructure.controller.response.VehiclesResponse;
import br.com.star.wars.infrastructure.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;

public final class VehiclesControllerMapper {

    private VehiclesControllerMapper() {

    }

    public static VehiclesBoundaryInput convertTo(final VehiclesRequest input) {
        return MapperUtils.map(input, VehiclesBoundaryInput.class);
    }
    public static VehiclesResponse convertTo(final VehiclesBoundaryOutput input) {
        final List<VehicleResponse> vehicleResponse = new ArrayList<>();
        if (input.getResults() != null) {
            input.getResults().forEach(vehicleApiClientOutput -> {
                vehicleResponse.add(MapperUtils.map(vehicleApiClientOutput, VehicleResponse.class));
            });
        }

        return MapperUtils.map(input, VehiclesResponse.class, result -> {
            result.setResults(vehicleResponse);
        });
    }

}
