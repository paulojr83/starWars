package br.com.star.wars.core.film;

import br.com.star.wars.core.film.io.FilmBoundaryInput;
import br.com.star.wars.core.film.io.FilmBoundaryOutput;

public interface FilmBoundary {

    FilmBoundaryOutput execute(FilmBoundaryInput input) throws Exception;

}
