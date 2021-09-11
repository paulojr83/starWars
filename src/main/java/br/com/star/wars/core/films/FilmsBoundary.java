package br.com.star.wars.core.films;

import br.com.star.wars.core.films.io.FilmsBoundaryInput;
import br.com.star.wars.core.films.io.FilmsBoundaryOutput;

public interface FilmsBoundary {

    FilmsBoundaryOutput execute(FilmsBoundaryInput input) throws Exception;

}
