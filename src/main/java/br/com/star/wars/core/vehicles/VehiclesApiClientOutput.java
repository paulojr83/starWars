package br.com.star.wars.core.vehicles;

import br.com.star.wars.core.vehicle.VehicleApiClientOutput;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VehiclesApiClientOutput {

    private Integer count;
    private String next;
    private String previous;
    private List<VehicleApiClientOutput> results;
}
