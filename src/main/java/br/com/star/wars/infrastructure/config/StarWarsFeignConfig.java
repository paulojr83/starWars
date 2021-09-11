package br.com.star.wars.infrastructure.config;

import br.com.star.wars.core.starwars.decoder.StarWarsDecoderBoundary;
import br.com.star.wars.infrastructure.apiclient.decoder.StarWarsDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StarWarsFeignConfig {

    private final StarWarsDecoderBoundary decoderBoundary;

    @Bean
    public StarWarsDecoder errorDecoder() {
        return new StarWarsDecoder(decoderBoundary);
    }
}
