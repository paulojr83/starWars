package br.com.star.wars.infrastructure.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmsResponse {

    private Integer count;
    private String next;
    private String previous;
    private List<FilmResponse> results;

}
