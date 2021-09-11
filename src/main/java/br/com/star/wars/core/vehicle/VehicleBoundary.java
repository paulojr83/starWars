package br.com.star.wars.core.vehicle;

import br.com.star.wars.core.vehicle.io.VehicleBoundaryInput;
import br.com.star.wars.core.vehicle.io.VehicleBoundaryOutput;

public interface VehicleBoundary {

    VehicleBoundaryOutput execute(VehicleBoundaryInput input) throws Exception;

}
