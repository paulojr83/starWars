package br.com.star.wars.core.vehicle.entity;

public final class VehicleEntity {

    private VehicleEntity() {

    }

    public static boolean isCapacityValid(final Integer capacity) {
        return capacity > 1;
    }

}
