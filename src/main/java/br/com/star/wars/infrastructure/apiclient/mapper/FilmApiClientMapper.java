package br.com.star.wars.infrastructure.apiclient.mapper;

import br.com.star.wars.core.film.FilmApiClientOutput;
import br.com.star.wars.core.films.FilmsApiClientOutput;
import br.com.star.wars.infrastructure.apiclient.response.FilmApiClientResponse;
import br.com.star.wars.infrastructure.apiclient.response.FilmsApiClientResponse;
import br.com.star.wars.infrastructure.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;

public final class FilmApiClientMapper {

    private FilmApiClientMapper() {

    }

    public static FilmApiClientOutput convertTo(final FilmApiClientResponse input) {
        return MapperUtils.map(input, FilmApiClientOutput.class);
    }

    public static FilmsApiClientOutput convertTo(final FilmsApiClientResponse input) {
        final List<FilmApiClientOutput> filmApiClientOutputs = new ArrayList<>();

        if (input.getResults() != null) {
            input.getResults().forEach(filmApiClientResponse -> {
                filmApiClientOutputs.add(MapperUtils.map(filmApiClientResponse, FilmApiClientOutput.class));
            });
        }
        return MapperUtils.map(input, FilmsApiClientOutput.class, result -> {
            result.setResults(filmApiClientOutputs);
        });
    }
}
