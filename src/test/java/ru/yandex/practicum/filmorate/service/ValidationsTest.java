package ru.yandex.practicum.filmorate.service;

import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidationsTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testUserInvalidEmail() {
        User user1 = new User(1, "invalidEmail", "login", "name", LocalDate.MIN);
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        assertFalse(violations.isEmpty());
        User user2 = new User(2, "", "login", "name", LocalDate.MIN);
        Set<ConstraintViolation<User>> violations2 = validator.validate(user1);
        assertFalse(violations2.isEmpty());
    }

    @Test
    public void testUserInvalidLogin() {
        User user1 = new User(1, "email@mail.com", "", "name", LocalDate.MIN);
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        assertFalse(violations.isEmpty());
        User user2 = new User(2, "email@mail.com", "log in", "name", LocalDate.MIN);
        Set<ConstraintViolation<User>> violations2 = validator.validate(user1);
        assertFalse(violations2.isEmpty());
    }

    @Test
    public void testUserInvalidBirthday() {
        User user1 = new User(1, "email@mail.com", "login", "name"
                , LocalDate.now().plusDays(1));
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testFilmInvalidName() {
        Film film = new Film(1, "", "description", LocalDate.MIN, 100);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testFilmInvalidDescription() {
        StringBuilder str = new StringBuilder();
        str.setLength(201);
        Film film = new Film(1, "name", str.toString(), LocalDate.MIN, 120);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testFilmInvalidReleaseDate() {
        Film film = new Film(1, "name", "description"
                , LocalDate.of(1895, 12, 27), 100);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testFilmInvalidDuration() {
        Film film = new Film(1, "name", "description", LocalDate.MIN, -1);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }
}
