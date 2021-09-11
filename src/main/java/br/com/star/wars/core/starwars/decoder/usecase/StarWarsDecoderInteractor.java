package br.com.star.wars.core.starwars.decoder.usecase;

import br.com.star.wars.core.starwars.decoder.StarWarsDecoderBoundary;
import br.com.star.wars.core.starwars.decoder.entity.StarWarsDecoderEntity;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class StarWarsDecoderInteractor implements StarWarsDecoderBoundary {

    @Override
    public Exception execute(final String methodKey, final Response response) {
        final var decodeObject = StarWarsDecoderEntity
                .builder()
                .content(response.body().toString())
                .build();
        return new ResponseStatusException(HttpStatus.valueOf(response.status()), decodeObject.getContent());
    }
}
