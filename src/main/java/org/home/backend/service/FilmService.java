package org.home.backend.service;

import org.home.backend.model.Film;
import org.home.backend.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>(filmRepository.findAll());
        films.sort(Comparator.comparingInt(Film::getYear).thenComparing(Film::getTitle));

        return films;
    }

    public Film getById(long id) {
        return (filmRepository.findById(id).isPresent())? filmRepository.findById(id).get() : null;
    }

    public void createOrUpdateFilm(Film film){
        filmRepository.save(film);
    }

    public void deleteFilm(long id) {
        filmRepository.deleteById(id);
    }
}
