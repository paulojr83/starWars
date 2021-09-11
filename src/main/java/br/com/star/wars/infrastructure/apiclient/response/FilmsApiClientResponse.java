package br.com.star.wars.infrastructure.apiclient.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@Builder
public class FilmsApiClientResponse {

    private Integer count;
    private String next;
    private String previous;
    private List<FilmApiClientResponse> results;
}
