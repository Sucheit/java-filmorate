package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.repository.FilmRepository;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class FilmController {

    private final FilmRepository filmRepository;

    public FilmController() {
        filmRepository = new FilmRepository();
    }

    @GetMapping("/films")
    public List<Film> findAll() {
        log.trace("Кол-во фильмов равно " + filmRepository.getFilms().size());
        return filmRepository.getFilms();
    }

    @PostMapping(value = "/films")
    public Film create(@Valid @RequestBody Film film) {
        log.trace("Фильм добавлен: " + film);
        return filmRepository.addFilm(film);
    }

    @PutMapping(value = "/films")
    public Film update(@Valid @RequestBody Film film) {
        log.trace("Фильм обновлен: " + film);
        return filmRepository.updateFilm(film);
    }
}
