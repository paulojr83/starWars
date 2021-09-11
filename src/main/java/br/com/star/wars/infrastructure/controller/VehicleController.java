package br.com.star.wars.infrastructure.controller;

import br.com.star.wars.core.vehicle.VehicleBoundary;
import br.com.star.wars.core.vehicles.VehiclesBoundary;
import br.com.star.wars.infrastructure.controller.mapper.VehicleControllerMapper;
import br.com.star.wars.infrastructure.controller.mapper.VehiclesControllerMapper;
import br.com.star.wars.infrastructure.controller.request.VehicleRequest;
import br.com.star.wars.infrastructure.controller.request.VehiclesRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/starwars/vehicles")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {

    private final VehicleBoundary vehicleBoundary;

    private final VehiclesBoundary vehiclesBoundary;

    @GetMapping("{id}")
    public ResponseEntity<?> getVehicle(final @PathVariable Integer id) throws Exception {
        log.info("M=getVehicle, endpoint do Vehicle id={}", id);
        try {
            if (id == null) {
                log.error("M=getVehicle, id inválido.");
                return ResponseEntity.badRequest().build();
            }
            final var inputBoundary = VehicleControllerMapper.convertTo(VehicleRequest
                    .builder().id(id).build());
            final var outputBoundary = vehicleBoundary.execute(inputBoundary);
            final var response = VehicleControllerMapper.convertTo(outputBoundary);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("M=getVehicle, erro na consulta ={}.", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getVehicles(final
            @RequestParam(value = "page", required = false) Integer page) throws Exception {
        log.info("M=getVehicles, endpoint do Vehicles page={}", page);
        try {
            final var inputBoundary = VehiclesControllerMapper.convertTo(VehiclesRequest
                    .builder().page(page).build());
            final var outputBoundary = vehiclesBoundary.execute(inputBoundary);
            final var response = VehiclesControllerMapper.convertTo(outputBoundary);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("M=getVehicles, erro na consulta ={}.", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
