package br.com.star.wars.infrastructure.controller.mapper;

import br.com.star.wars.core.film.io.FilmBoundaryOutput;
import br.com.star.wars.infrastructure.controller.request.FilmRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Film Controller Mapper Tests")
public class FilmControllerMapperTest {

    @Test
    @DisplayName("Deve verificar o mapeamento do FilmBoundaryInput.")
    public void shouldVerifyTheFilmBoundaryInputMap() {
        final var request = FilmRequest.builder().id(4).build();
        final var response = FilmControllerMapper.convertTo(request);

        Assertions.assertEquals(4, response.getId());
    }

    @Test
    @DisplayName("Deve verificar o mapeamento do FilmResponse.")
    public void shouldVerifyTheFilmResponseMap() {
        final var request = FilmBoundaryOutput.builder()
                .episodId(6).build();
        final var response = FilmControllerMapper.convertTo(request);

        Assertions.assertEquals(6, response.getEpisodId());
    }
}
