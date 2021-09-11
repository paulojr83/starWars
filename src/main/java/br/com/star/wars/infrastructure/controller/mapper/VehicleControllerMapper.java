package br.com.star.wars.infrastructure.controller.mapper;

import br.com.star.wars.core.vehicle.io.VehicleBoundaryInput;
import br.com.star.wars.core.vehicle.io.VehicleBoundaryOutput;
import br.com.star.wars.infrastructure.controller.request.VehicleRequest;
import br.com.star.wars.infrastructure.controller.response.VehicleResponse;
import br.com.star.wars.infrastructure.utils.MapperUtils;

public final class VehicleControllerMapper {

    private VehicleControllerMapper() {

    }

    public static VehicleBoundaryInput convertTo(final VehicleRequest input) {
        return MapperUtils.map(input, VehicleBoundaryInput.class);
    }
    public static VehicleResponse convertTo(final VehicleBoundaryOutput input) {
        return MapperUtils.map(input, VehicleResponse.class);
    }

}
