package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;


public class ValidationService {

    private static final Integer MAX_FILM_DESCRIPTION_LENGTH = 200;
    private static final LocalDate MIN_FILM_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    public static void validateUser(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ValidationException("Адрес электронной почты не может быть пустым.");
        }
        if (!user.getEmail().contains("@")) {
            throw new ValidationException("Адрес электронной почты должен содержать знак @.");
        }
        if (user.getLogin().isBlank() || user.getLogin().contains(" ")) {
            throw new ValidationException("Логин не может быть пустым или содержать пробелы.");
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Дата рождения не может быть в будующем.");
        }
    }

    public static void validateFilm(Film film) {
        if (film.getName().isBlank()) {
            throw new ValidationException("Название фильма не может быть пустым.");
        }
        if (film.getDescription().length() > MAX_FILM_DESCRIPTION_LENGTH) {
            throw new ValidationException("Длина описания фильма не может быть больше 200.");
        }
        if (film.getReleaseDate().isBefore(MIN_FILM_RELEASE_DATE)) {
            throw new ValidationException("Дата релиза фильма не может быть раньше 28.12.1895.");
        }
        if (film.getDuration() < 0) {
            throw new ValidationException("Длительность фильма не может быть отрицательной.");
        }
    }
}
