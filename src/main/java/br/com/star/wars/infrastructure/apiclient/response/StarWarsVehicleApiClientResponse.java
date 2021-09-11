package br.com.star.wars.infrastructure.apiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Data
@Builder
public class StarWarsVehicleApiClientResponse {

    private String name;
    private String model;
    private String manufacturer;

    @JsonProperty("cost_in_credits")
    private String costInCredits;
    private String length;

    @JsonProperty("max_atmosphering_speed")
    private String maxAtmospheringSpeed;
    private String crew;
    private String passengers;

    @JsonProperty("cargo_capacity")
    private String cargoCapacity;

    private String consumables;

    @JsonProperty("vehicle_class")
    private String vehicleClass;

    private List<String> pilots;
    private List<String> films;

    private LocalDateTime created;
    private LocalDateTime edited;

    private String url;

}
