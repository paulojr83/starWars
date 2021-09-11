package br.com.star.wars.core.films.entity;

import br.com.star.wars.core.film.FilmApiClientOutput;
import br.com.star.wars.core.films.io.FilmsBoundaryInput;

import java.util.List;

public final class FilmsEntity {

    private FilmsEntity() {

    }

    public static boolean isValidResult(final List<FilmApiClientOutput> filmApiClientOutputs) {
        return !filmApiClientOutputs.isEmpty();
    }

    public static FilmsBoundaryInput setDefaultPage(final FilmsBoundaryInput input) {
        if (input.getPage() == null || input.getPage() < 1) {
            input.setPage(1);
        }
        return input;
    }
}
