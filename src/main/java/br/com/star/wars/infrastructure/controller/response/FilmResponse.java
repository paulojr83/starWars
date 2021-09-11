package br.com.star.wars.infrastructure.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmResponse {

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
