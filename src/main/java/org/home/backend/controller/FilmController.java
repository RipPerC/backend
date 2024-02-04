package org.home.backend.controller;

import org.home.backend.model.Film;
import org.home.backend.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping("/v1")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/film")
    public ResponseEntity<List<Film>> getAll() {

        try {
            List<Film> films = new ArrayList<>();
            filmRepository.findAll().forEach(films::add);
            films.sort(Comparator.comparingInt(Film::getYear).thenComparing(Film::getTitle));

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
            Film film = filmRepository.findById(id).get();

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
        if(film.getDownloaded() == null) {
            film.setDownloaded(false);
        }

        try {
            Film _film = filmRepository.save(film);

            if (film.getId() != 0) {
                return new ResponseEntity<>(_film, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(_film, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/film/{id}")
    public ResponseEntity<HttpStatus> deleteFilm(@PathVariable("id") long id) {
        try {
            filmRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

