package br.com.star.wars.core.films.usecase;

import br.com.star.wars.core.films.FilmsBoundary;
import br.com.star.wars.core.films.io.FilmsBoundaryInput;
import br.com.star.wars.infrastructure.apiclient.StarWarsFilmGatewayApiClient;
import br.com.star.wars.infrastructure.apiclient.feign.StarWarsFeign;
import br.com.star.wars.infrastructure.apiclient.response.FilmApiClientResponse;
import br.com.star.wars.infrastructure.apiclient.response.FilmsApiClientResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
@DisplayName("Films Tests")
public class FilmsInteractorTest {

    private FilmsBoundary boundary;

    @Mock
    private StarWarsFeign feign;

    @BeforeEach
    public void setUp() {
        final var gatewayApiClient = new StarWarsFilmGatewayApiClient(feign);
        boundary = new FilmsInteractor(gatewayApiClient);
    }

    @Test
    @DisplayName("Deve verificar Films.")
    public void shouldVerifyFilms() throws Exception {
        final var input = FilmsBoundaryInput.builder().page(2).build();
        final var mockResponse = FilmsApiClientResponse
                .builder()
                .results(Arrays.asList(FilmApiClientResponse.builder().build()))
                .count(2).build();
        Mockito.when(feign.getFilms(Mockito.any())).thenReturn(mockResponse);
        final var outputBoundary = boundary.execute(input);
        Assertions.assertEquals(Integer.valueOf(2), outputBoundary.getCount());
    }

    @Test
    @DisplayName("Deve levantar exceção quando result for vazio.")
    public void shouldThrowExceptionWhenResultIsEmpty() {
        final var input = FilmsBoundaryInput.builder().page(2).build();
        final var mockResponse = FilmsApiClientResponse
                .builder().build();
        Mockito.when(feign.getFilms(Mockito.any())).thenReturn(mockResponse);
        final var erroMessage = Assertions.assertThrows(Exception.class, () -> {
            boundary.execute(input);
        });

        Assertions.assertEquals("Resultado inválido!", erroMessage.getMessage());
    }

    @Test
    @DisplayName("Deve setar valor de default quando o page for nulo.")
    public void shouldSetDefaulValueWhenPageIsNull() throws Exception {
        final var input = FilmsBoundaryInput.builder().build();
        final var mockResponse = FilmsApiClientResponse
                .builder()
                .results(Arrays.asList(FilmApiClientResponse.builder().build()))
                .count(2).build();
        Mockito.when(feign.getFilms(Mockito.any())).thenReturn(mockResponse);
        final var outputBoundary = boundary.execute(input);

        Assertions.assertEquals(Integer.valueOf(2), outputBoundary.getCount());
    }
}
