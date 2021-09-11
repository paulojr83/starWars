package br.com.star.wars.core.vehicles;

import br.com.star.wars.core.vehicles.io.VehiclesBoundaryInput;
import br.com.star.wars.core.vehicles.io.VehiclesBoundaryOutput;

public interface VehiclesBoundary {

    VehiclesBoundaryOutput execute(VehiclesBoundaryInput input) throws Exception;

}
