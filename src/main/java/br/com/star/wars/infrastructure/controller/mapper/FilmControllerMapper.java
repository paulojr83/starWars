package br.com.star.wars.infrastructure.controller.mapper;

import br.com.star.wars.core.film.io.FilmBoundaryInput;
import br.com.star.wars.core.film.io.FilmBoundaryOutput;
import br.com.star.wars.infrastructure.controller.request.FilmRequest;
import br.com.star.wars.infrastructure.controller.response.FilmResponse;
import br.com.star.wars.infrastructure.utils.MapperUtils;

public final class FilmControllerMapper {

    private FilmControllerMapper() {

    }

    public static FilmBoundaryInput convertTo(final FilmRequest input) {
        return MapperUtils.map(input, FilmBoundaryInput.class);
    }

    public static FilmResponse convertTo(final FilmBoundaryOutput input) {
        return MapperUtils.map(input, FilmResponse.class);
    }

}
