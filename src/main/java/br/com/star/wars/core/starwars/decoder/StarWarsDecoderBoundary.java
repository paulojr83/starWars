package br.com.star.wars.core.starwars.decoder;

import feign.Response;

public interface StarWarsDecoderBoundary {

    Exception execute(String methodKey, Response response);

}
