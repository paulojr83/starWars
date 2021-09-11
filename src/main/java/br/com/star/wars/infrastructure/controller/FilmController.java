package br.com.star.wars.infrastructure.controller;

import br.com.star.wars.core.film.FilmBoundary;
import br.com.star.wars.core.films.FilmsBoundary;
import br.com.star.wars.infrastructure.controller.mapper.FilmControllerMapper;
import br.com.star.wars.infrastructure.controller.mapper.FilmsControllerMapper;
import br.com.star.wars.infrastructure.controller.request.FilmRequest;
import br.com.star.wars.infrastructure.controller.request.FilmsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/starwars/films")
@RequiredArgsConstructor
@Slf4j
public class FilmController {

    private final FilmBoundary filmBoundary;

    private final FilmsBoundary filmsBoundary;

    @GetMapping("{id}")
    public ResponseEntity<?> getFilm(final @PathVariable Integer id) throws Exception {
        log.info("M=getFilm, endpoint do Film id={}", id);
        try {
            if (id == null) {
                log.error("M=getFilm, id inválido.");
                return ResponseEntity.badRequest().build();
            }
            final var inputBoundary = FilmControllerMapper
                    .convertTo(FilmRequest.builder().id(id).build());
            final var outputBoundary = filmBoundary.execute(inputBoundary);
            final var response = FilmControllerMapper.convertTo(outputBoundary);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("M=getFilm, erro na consulta ={}.", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getFilms(final
                                         @RequestParam(value = "page", required = false) Integer page) {
        log.info("M=getFilms, endpoint do Films page={}", page);
        try {
            final var inputBoundary = FilmsControllerMapper
                    .convertTo(FilmsRequest.builder().page(page).build());
            final var outputBoundary = filmsBoundary.execute(inputBoundary);
            final var response = FilmsControllerMapper.convertTo(outputBoundary);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("M=getFilms, erro na consulta ={}.", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
