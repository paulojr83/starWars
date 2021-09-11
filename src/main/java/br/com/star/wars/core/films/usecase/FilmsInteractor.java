package br.com.star.wars.core.films.usecase;

import br.com.star.wars.core.film.FilmGatewayApiClient;
import br.com.star.wars.core.films.FilmsApiClientInput;
import br.com.star.wars.core.films.FilmsBoundary;
import br.com.star.wars.core.films.entity.FilmsEntity;
import br.com.star.wars.core.films.io.FilmsBoundaryInput;
import br.com.star.wars.core.films.io.FilmsBoundaryOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FilmsInteractor implements FilmsBoundary {

    private final FilmGatewayApiClient apiClient;

    @Override
    public FilmsBoundaryOutput execute(final FilmsBoundaryInput input) throws Exception {
        log.info("M=execute, executando FilmsInteractor input.getPage()={}", input.getPage());

        final var page = FilmsEntity.setDefaultPage(input);

        final var outputApiClient = apiClient.getFilms(FilmsApiClientInput
                .builder().page(page.getPage()).build());

        if (!FilmsEntity.isValidResult(outputApiClient.getResults())) {
            throw new Exception("Resultado inválido!");
        }

        return FilmsBoundaryOutput
                .builder()
                .count(outputApiClient.getCount())
                .previous(outputApiClient.getPrevious())
                .next(outputApiClient.getNext())
                .results(outputApiClient.getResults())
                .build();
    }
}
