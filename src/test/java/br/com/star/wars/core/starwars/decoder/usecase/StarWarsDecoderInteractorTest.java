package br.com.star.wars.core.starwars.decoder.usecase;

import br.com.star.wars.core.starwars.decoder.StarWarsDecoderBoundary;
import feign.Request;
import feign.Response;
import feign.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@DisplayName("StarWarsDecoder Tests")
public class StarWarsDecoderInteractorTest {

    private StarWarsDecoderBoundary boundary;


    @BeforeEach
    public void setUp() {
        boundary = new StarWarsDecoderInteractor();
    }

    @Test
    @DisplayName("Deve verificar StarWarsDecoder.")
    public void shouldVerifyStarWarsDecoder() {
        final var target = boundary.execute("200", getResponse());
        Assertions.assertEquals(
                "org.springframework.web.server.ResponseStatusException: 400 BAD_REQUEST \"response body\"",
                target.toString());
    }

    private Response getResponse() {
        final String content = "response body";
        final InputStream inputStream = new ByteArrayInputStream(content.getBytes(UTF_8));
        final Map<String, Collection<String>> headers = new HashMap<>();
        headers.put("Content-Type", Collections.singleton("text/plain"));
        return Response.builder()
                .status(400)
                .reason("OK")
                .headers(headers)
                .request(Request.create(Request.HttpMethod.GET, "/api", Collections.emptyMap(), null, Util.UTF_8))
                .body(inputStream, content.length())
                .build();
    }
}
