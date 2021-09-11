package br.com.star.wars.infrastructure.controller.mapper;

import br.com.star.wars.core.film.FilmApiClientOutput;
import br.com.star.wars.core.films.io.FilmsBoundaryOutput;
import br.com.star.wars.infrastructure.apiclient.mapper.FilmApiClientMapper;
import br.com.star.wars.infrastructure.apiclient.response.FilmApiClientResponse;
import br.com.star.wars.infrastructure.apiclient.response.FilmsApiClientResponse;
import br.com.star.wars.infrastructure.controller.request.FilmsRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@DisplayName("Films Controller Mapper Tests")
public class FilmsControllerMapperTest {

    @Test
    @DisplayName("Deve verificar o mapeamento do FilmsBoundaryInput.")
    public void shouldVerifyTheFilmsBoundaryInputMap() {
        final var request = FilmsRequest.builder().page(4).build();
        final var response = FilmsControllerMapper.convertTo(request);

        Assertions.assertEquals(Integer.valueOf("4"), response.getPage());
    }

    @Test
    @DisplayName("Deve verificar o mapeamento do FilmsResponse.")
    public void shouldVerifyTheFilmsResponseMap() {
        final var request = FilmsBoundaryOutput.builder()
                .count(3).build();
        final var response = FilmsControllerMapper.convertTo(request);

        Assertions.assertEquals(Integer.valueOf(3), response.getCount());
    }

    @Test
    @DisplayName("Deve verificar o mapeamento do FilmsResponse com result preenchido.")
    public void shouldVerifyTheFilmsResponseMapWithResultFilled() {
        final var request = FilmsBoundaryOutput.builder()
                .results(Arrays.asList(FilmApiClientOutput.builder()
                        .build()))
                .count(3).build();
        final var response = FilmsControllerMapper.convertTo(request);

        Assertions.assertEquals(Integer.valueOf(3), response.getCount());
    }

    @Test
    @DisplayName("Deve verificar o mapeamento do FilmApiClientOutput.")
    public void shouldVerifyTheFilmApiClientOutputMap() {
        final var response = FilmsApiClientResponse
                .builder()
                .results(Arrays.asList(FilmApiClientResponse.builder()
                        .build()))
                .count(2).build();
        final var apiClientOutput = FilmApiClientMapper.convertTo(response);

        Assertions.assertFalse(apiClientOutput.getResults().isEmpty());
    }
}
