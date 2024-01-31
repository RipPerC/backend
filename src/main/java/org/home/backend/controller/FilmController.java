package org.home.backend.controller;

import org.home.backend.model.Film;
import org.home.backend.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/film")
    public ResponseEntity<List<Film>> getAll() {

        try {
            List<Film> films = new ArrayList<Film>();
            filmRepository.findAll().forEach(films::add);

            if (films.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(films, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/film")
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        try {
            Film _film = filmRepository
                    .save(new Film(film.getTitle(), film.getDirector(), film.getYear(), film.getAudio(),
                            film.getSubtitle(), film.getFormat(), film.getDownloaded()));
            return new ResponseEntity<>(_film, HttpStatus.CREATED);
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

