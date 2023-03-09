package ru.yandex.practicum.filmorate.dao;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmRepository {

    private final Map<Long, Film> films;

    private long id;

    public FilmRepository() {
        films = new HashMap<>();
        id = 0;
    }

    private long generateId() {
        return ++id;
    }

    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    public Film addFilm(Film film) {
        film.setId(generateId());
        films.put(film.getId(), film);
        return films.get(film.getId());
    }

    public Film updateFilm(Film film) {
        if (!films.containsKey(film.getId())) {
            throw new RuntimeException("Фильма с id = " + film.getId() + " не существует.");
        }
        films.put(film.getId(), film);
        return film;
    }
}
