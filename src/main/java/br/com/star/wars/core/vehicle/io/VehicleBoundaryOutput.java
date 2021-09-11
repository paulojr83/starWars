package br.com.star.wars.core.vehicle.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class VehicleBoundaryOutput {

    private String name;
    private String model;
    private String manufacturer;

    private String costInCredits;
    private String length;

    private String maxAtmospheringSpeed;
    private String crew;
    private String passengers;

    private String cargoCapacity;

    private String consumables;

    private String vehicleClass;

    private List<String> pilots;
    private List<String> films;

    private LocalDateTime created;
    private LocalDateTime edited;

    private String url;

}
