package br.com.star.wars.infrastructure.apiclient.decoder;

import br.com.star.wars.core.starwars.decoder.StarWarsDecoderBoundary;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class StarWarsDecoder implements ErrorDecoder {

    private final StarWarsDecoderBoundary boundary;

    @Override
    public Exception decode(final String methodKey, final Response response) {
        return boundary.execute(methodKey, response);
    }
}
