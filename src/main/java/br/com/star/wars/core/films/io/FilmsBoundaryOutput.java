package br.com.star.wars.core.films.io;

import br.com.star.wars.core.film.FilmApiClientOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class FilmsBoundaryOutput {

    private Integer count;
    private String next;
    private String previous;
    private List<FilmApiClientOutput> results;

}
