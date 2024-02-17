package org.home.backend.controller;

import org.home.backend.model.Film;
import org.home.backend.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/v1")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/film")
    public ResponseEntity<List<Film>> getAll() {

        try {

            List<Film> films = filmService.getAllFilms();

            if (films.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(films, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/film/{id}")
    public ResponseEntity<Film> getById(@PathVariable("id") long id) {

        try {

            Film film = filmService.getById(id);

            if (film == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(film, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/film")
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        formattingFilm(film);

        boolean newFilm = film.getId() == 0;

        try {
            filmService.createOrUpdateFilm(film);

            if (newFilm) {
                return new ResponseEntity<>(film, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(film, HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/film/{id}")
    public ResponseEntity<HttpStatus> deleteFilm(@PathVariable("id") long id) {
        try {
            filmService.deleteFilm(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void formattingFilm(Film film) {
        if(film.getDownloaded() == null) {
            film.setDownloaded(false);
        }

        if (film.getAudio() != null) {
            film.setAudio(film.getAudio().toUpperCase());
        }

        if (film.getSubtitle() != null) {
            film.setSubtitle(film.getSubtitle().toUpperCase());
        }

        if (film.getFormat() != null) {
            film.setFormat(film.getFormat().toLowerCase());
        }
    }

}

