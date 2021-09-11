package br.com.star.wars.core.vehicles.io;

import br.com.star.wars.core.vehicle.VehicleApiClientOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class VehiclesBoundaryOutput {

    private Integer count;
    private String next;
    private String previous;
    private List<VehicleApiClientOutput> results;

}
