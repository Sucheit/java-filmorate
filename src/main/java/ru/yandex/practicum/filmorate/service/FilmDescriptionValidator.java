package ru.yandex.practicum.filmorate.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FilmDescriptionValidator implements ConstraintValidator<FilmDescription, String> {

    private static final Integer MAX_FILM_DESCRIPTION_LENGTH = 200;

    @Override
    public boolean isValid(String filmDescription, ConstraintValidatorContext context) {
        return filmDescription.length() <= MAX_FILM_DESCRIPTION_LENGTH;
    }
}
