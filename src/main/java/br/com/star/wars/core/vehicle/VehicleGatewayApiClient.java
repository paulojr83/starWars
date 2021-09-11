package br.com.star.wars.core.vehicle;

import br.com.star.wars.core.vehicles.VehiclesApiClientInput;
import br.com.star.wars.core.vehicles.VehiclesApiClientOutput;

public interface VehicleGatewayApiClient {

    VehicleApiClientOutput getVehicle(VehicleApiClientInput input);

    VehiclesApiClientOutput getVehicles(VehiclesApiClientInput input);
}
