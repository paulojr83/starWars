package br.com.star.wars.core.film.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class FilmBoundaryOutput {

    private String title;
    private Integer episodId;
    private String openingCrawl;

    private String director;
    private String producer;
    private LocalDate releaseDate;

    private List<String> characters;

    private List<String> planets;

    private List<String> starships;

    private List<String> vehicles;

    private List<String> species;

    private LocalDateTime created;
    private LocalDateTime edited;

    private String url;

}
