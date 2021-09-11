package br.com.star.wars.core.vehicles.entity;

import br.com.star.wars.core.vehicle.VehicleApiClientOutput;
import br.com.star.wars.core.vehicles.io.VehiclesBoundaryInput;

import java.util.List;

public final class VehiclesEntity {

    private VehiclesEntity() {

    }

    public static boolean isValidResult(final List<VehicleApiClientOutput> vehiclesResponses) {
        return !vehiclesResponses.isEmpty();
    }

    public static VehiclesBoundaryInput setDefaultPage(final VehiclesBoundaryInput input) {
        if (input.getPage() == null || input.getPage() < 1) {
            input.setPage(1);
        }
        return input;
    }

}
