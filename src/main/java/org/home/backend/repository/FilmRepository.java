package org.home.backend.repository;

import org.home.backend.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
    //Nothing
}
