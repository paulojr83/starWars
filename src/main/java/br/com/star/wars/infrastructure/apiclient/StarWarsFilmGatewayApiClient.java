package br.com.star.wars.infrastructure.apiclient;

import br.com.star.wars.core.film.FilmApiClientInput;
import br.com.star.wars.core.film.FilmApiClientOutput;
import br.com.star.wars.core.film.FilmGatewayApiClient;
import br.com.star.wars.core.films.FilmsApiClientInput;
import br.com.star.wars.core.films.FilmsApiClientOutput;
import br.com.star.wars.infrastructure.apiclient.feign.StarWarsFeign;
import br.com.star.wars.infrastructure.apiclient.mapper.FilmApiClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StarWarsFilmGatewayApiClient implements FilmGatewayApiClient {

    private final StarWarsFeign feign;

    @Override
    public FilmApiClientOutput getFilm(final FilmApiClientInput input) {
        log.info("M=getFilm, executando endpoint film do StarWars.");
        final var response = feign.getFilm(input.getId());
        return FilmApiClientMapper.convertTo(response);
    }

    @Override
    public FilmsApiClientOutput getFilms(final FilmsApiClientInput input) {
        log.info("M=getFilms, executando endpoint films do StarWars.");
        final var response = feign.getFilms(input.getPage());
        return FilmApiClientMapper.convertTo(response);
    }
}
