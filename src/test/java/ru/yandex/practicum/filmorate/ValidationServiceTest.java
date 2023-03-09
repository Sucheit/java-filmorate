package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.ValidationService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationServiceTest {

    @Test
    public void shouldThrowUserEmailIsEmpty() {
        User user = new User(1,"","login","name", LocalDate.MIN);
        assertThrows(ValidationException.class, () -> ValidationService.validateUser(user));
    }

    @Test
    public void shouldThrowUserEmailIsBlank() {
        User user = new User(1,"   ","login","name", LocalDate.MIN);
        assertThrows(ValidationException.class, () -> ValidationService.validateUser(user));
    }

    @Test
    public void shouldThrowUserEmailIsNULL() {
        User user = new User(1,null,"login","name", LocalDate.MIN);
        assertThrows(ValidationException.class, () -> ValidationService.validateUser(user));
    }

    @Test
    public void shouldThrowUserLoginHaveBlanks() {
        User user = new User(1,"email@com","log in","name", LocalDate.MIN);
        assertThrows(ValidationException.class, () -> ValidationService.validateUser(user));
    }

    @Test
    public void shouldThrowUserBirthdayIsInFuture() {
        User user = new User(1,"email@com","login","name", LocalDate.now().plusDays(1));
        assertThrows(ValidationException.class, () -> ValidationService.validateUser(user));
    }

    @Test
    public void shouldThrowFilmNameIsBlank() {
        Film film = new Film(1, "  ", "description", LocalDate.now(), 100);
        assertThrows(ValidationException.class, () -> ValidationService.validateFilm(film));
    }

    @Test
    public void shouldThrowFilmDescriptionIsOver200() {
        StringBuilder str = new StringBuilder();
        str.setLength(201);
        Film film = new Film(1, "name", str.toString(), LocalDate.now(), 100);
        assertThrows(ValidationException.class, () -> ValidationService.validateFilm(film));
    }

    @Test
    public void shouldThrowFilmIncorrectReleaseDate() {
        Film film = new Film(1, "name", "description",
                LocalDate.of(1895, 12, 27), 100);
        assertThrows(ValidationException.class, () -> ValidationService.validateFilm(film));
    }

    @Test
    public void shouldThrowFilmDurationIsNegative() {
        Film film = new Film(1, "name", "description", LocalDate.now(), -1);
        assertThrows(ValidationException.class, () -> ValidationService.validateFilm(film));
    }
}

