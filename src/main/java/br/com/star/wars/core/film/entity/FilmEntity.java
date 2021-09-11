package br.com.star.wars.core.film.entity;

public final class FilmEntity {

    private FilmEntity() {

    }

    public static boolean isEpisodeValid(final Integer episode) {
        return episode > 0;
    }

}
