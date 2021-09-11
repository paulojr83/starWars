package br.com.star.wars.core.film.usecase;

import br.com.star.wars.core.film.FilmBoundary;
import br.com.star.wars.core.film.io.FilmBoundaryInput;
import br.com.star.wars.infrastructure.apiclient.StarWarsFilmGatewayApiClient;
import br.com.star.wars.infrastructure.apiclient.feign.StarWarsFeign;
import br.com.star.wars.infrastructure.apiclient.response.FilmApiClientResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Film Tests")
public class FilmInteractorTest {

    private FilmBoundary boundary;

    @Mock
    private StarWarsFeign feign;

    @BeforeEach
    public void setUp() {
        final var gatewayApiClient = new StarWarsFilmGatewayApiClient(feign);
        boundary = new FilmInteractor(gatewayApiClient);
    }


    @Test
    @DisplayName("Deve verificar Film.")
    public void shouldVerifyFilm() throws Exception {
        final var input = FilmBoundaryInput.builder().id(Integer.valueOf(2)).build();
        final var mockResponse = FilmApiClientResponse
                .builder().episodId(2).build();
        Mockito.when(feign.getFilm(Mockito.any())).thenReturn(mockResponse);
        final var outputBoundary = boundary.execute(input);
        Assertions.assertEquals(2, outputBoundary.getEpisodId());
    }

    @Test
    @DisplayName("Deve levantar exceção quando Vehicle capacity for menor do que 2.")
    public void shouldThrowExceptionWhenVehicleCapacityIsLessThan2() throws Exception {
        final var input = FilmBoundaryInput.builder().id(Integer.valueOf(2)).build();
        final var mockResponse = FilmApiClientResponse
                .builder().episodId(0).build();
        Mockito.when(feign.getFilm(Mockito.any())).thenReturn(mockResponse);
        final var erroMessage = Assertions.assertThrows(Exception.class, () -> {
            boundary.execute(input);
        });

        Assertions.assertEquals("Episodio invalido!", erroMessage.getMessage());
    }
}
