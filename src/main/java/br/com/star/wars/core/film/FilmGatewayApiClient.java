package br.com.star.wars.core.film;

import br.com.star.wars.core.films.FilmsApiClientInput;
import br.com.star.wars.core.films.FilmsApiClientOutput;

public interface FilmGatewayApiClient {

    FilmApiClientOutput getFilm(FilmApiClientInput input);

    FilmsApiClientOutput getFilms(FilmsApiClientInput input);
}
