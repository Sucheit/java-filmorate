package ru.yandex.practicum.filmorate.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.List;

public class FilmRepositoryTest {

    private FilmRepository filmRepository;

    @BeforeEach
    public void beforeEach() {
        filmRepository = new FilmRepository();
        filmRepository.addFilm(new Film("name1", "description1", LocalDate.now(), 100));
        filmRepository.addFilm(new Film("name2", "description2", LocalDate.now(), 120));
    }

    @Test
    public void getAllFilm() {
        List<Film> films = filmRepository.getFilms();
        Assertions.assertNotNull(films, "Не возвращает список фильмов");
        Assertions.assertEquals(2, films.size(), "Не верное кол-во фильмов");
    }

    @Test
    public void addFilm() {
        Film film = new Film("name3", "description3", LocalDate.now(), 90);
        filmRepository.addFilm(film);
        Assertions.assertEquals(3, film.getId(), "Не верно сгенерировался id");
        Assertions.assertEquals(3, filmRepository.getFilms().size(), "Не верное кол-во фильмов");
    }

    @Test
    public void updateFilm() {
        Film updatedFilm = filmRepository.updateFilm(new Film(2, "newName",
                "newDescription", LocalDate.now(), 140));
        Assertions.assertEquals("newName", updatedFilm.getName()
                , "Не верно обновился название фильма.");
        Assertions.assertEquals("newDescription", updatedFilm.getDescription()
                , "Не верно обновилось описание фильма.");
        Assertions.assertEquals(140, updatedFilm.getDuration()
                , "Не верно обновилась длительность фильма.");
        Assertions.assertThrows(RuntimeException.class, () -> filmRepository.updateFilm(new Film(3, "newName",
                "newDescription", LocalDate.now(), 140)), "Обновляется фильм с неверным id.");
    }
}
