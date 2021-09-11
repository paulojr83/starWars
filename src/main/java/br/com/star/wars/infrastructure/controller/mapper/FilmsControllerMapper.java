package br.com.star.wars.infrastructure.controller.mapper;

import br.com.star.wars.core.films.io.FilmsBoundaryInput;
import br.com.star.wars.core.films.io.FilmsBoundaryOutput;
import br.com.star.wars.infrastructure.controller.request.FilmsRequest;
import br.com.star.wars.infrastructure.controller.response.FilmResponse;
import br.com.star.wars.infrastructure.controller.response.FilmsResponse;
import br.com.star.wars.infrastructure.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;

public final class FilmsControllerMapper {

    private FilmsControllerMapper() {

    }

    public static FilmsBoundaryInput convertTo(final FilmsRequest input) {
        return MapperUtils.map(input, FilmsBoundaryInput.class);
    }
    public static FilmsResponse convertTo(final FilmsBoundaryOutput input) {
        final List<FilmResponse> filmResponses = new ArrayList<>();
        if (input.getResults() != null) {
            input.getResults().forEach(filmApiClientOutput -> {
                filmResponses.add(MapperUtils.map(filmApiClientOutput, FilmResponse.class));
            });
        }
        return MapperUtils.map(input, FilmsResponse.class, result -> {
            result.setResults(filmResponses);
        });
    }

}
