package br.com.star.wars.core.films;

import br.com.star.wars.core.film.FilmApiClientOutput;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FilmsApiClientOutput {

    private Integer count;
    private String next;
    private String previous;
    private List<FilmApiClientOutput> results;
}
