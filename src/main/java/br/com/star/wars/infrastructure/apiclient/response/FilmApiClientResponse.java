package br.com.star.wars.infrastructure.apiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Data
@Builder
public class FilmApiClientResponse {

    private String title;

    @JsonProperty("episode_id")
    private Integer episodId;

    @JsonProperty("opening_crawl")
    private String openingCrawl;

    private String director;
    private String producer;

    @JsonProperty("release_date")
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
