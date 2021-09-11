package br.com.star.wars.infrastructure.apiclient.feign;

import br.com.star.wars.infrastructure.apiclient.response.FilmApiClientResponse;
import br.com.star.wars.infrastructure.apiclient.response.FilmsApiClientResponse;
import br.com.star.wars.infrastructure.apiclient.response.StarWarsVehicleApiClientResponse;
import br.com.star.wars.infrastructure.apiclient.response.StarWarsVehiclesApiClientResponse;
import br.com.star.wars.infrastructure.config.StarWarsFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "StarWars", url = "${infrastructure.apiclient.starwars.url}", configuration = StarWarsFeignConfig.class)
public interface StarWarsFeign {

    @GetMapping("/vehicles/{id}")
    StarWarsVehicleApiClientResponse getVehicle(@PathVariable Integer id);

    @GetMapping("/vehicles/")
    StarWarsVehiclesApiClientResponse getVehicles(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page);

    @GetMapping("/films/{id}")
    FilmApiClientResponse getFilm(@PathVariable Integer id);

    @GetMapping("/films/")
    FilmsApiClientResponse getFilms(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page);

}
