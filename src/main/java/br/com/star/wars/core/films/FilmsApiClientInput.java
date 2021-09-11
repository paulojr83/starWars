package br.com.star.wars.core.films;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FilmsApiClientInput {

    private Integer page;
}
