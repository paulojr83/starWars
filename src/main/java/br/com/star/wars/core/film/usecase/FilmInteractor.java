package br.com.star.wars.core.film.usecase;

import br.com.star.wars.core.film.FilmApiClientInput;
import br.com.star.wars.core.film.FilmApiClientOutput;
import br.com.star.wars.core.film.FilmBoundary;
import br.com.star.wars.core.film.FilmGatewayApiClient;
import br.com.star.wars.core.film.entity.FilmEntity;
import br.com.star.wars.core.film.io.FilmBoundaryInput;
import br.com.star.wars.core.film.io.FilmBoundaryOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FilmInteractor implements FilmBoundary {

    private final FilmGatewayApiClient apiClient;

    @Override
    public FilmBoundaryOutput execute(final FilmBoundaryInput input) throws Exception {
        log.info("M=execute, executando FilmInteractor input.getId()={}", input.getId());

        final var apiClientOutput = apiClient.getFilm(FilmApiClientInput.builder()
                .id(input.getId()).build());

        if (!FilmEntity.isEpisodeValid(apiClientOutput.getEpisodId())) {
            throw new Exception("Episodio invalido!");
        }

        return getFilmBoundaryOutput(apiClientOutput);
    }

    private FilmBoundaryOutput getFilmBoundaryOutput(final FilmApiClientOutput apiClientOutput) {
        return FilmBoundaryOutput
                .builder()
                .characters(apiClientOutput.getCharacters())
                .created(apiClientOutput.getCreated())
                .edited(apiClientOutput.getEdited())
                .director(apiClientOutput.getDirector())
                .episodId(apiClientOutput.getEpisodId())
                .openingCrawl(apiClientOutput.getOpeningCrawl())
                .planets(apiClientOutput.getPlanets())
                .producer(apiClientOutput.getProducer())
                .releaseDate(apiClientOutput.getReleaseDate())
                .species(apiClientOutput.getSpecies())
                .url(apiClientOutput.getUrl())
                .starships(apiClientOutput.getStarships())
                .title(apiClientOutput.getTitle())
                .vehicles(apiClientOutput.getVehicles())
                .build();
    }
}
