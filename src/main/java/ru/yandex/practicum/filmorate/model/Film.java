package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.service.FilmDescription;
import ru.yandex.practicum.filmorate.service.FilmReleaseDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {

    private long id;

    @NotBlank
    private String name;

    @FilmDescription
    private String description;

    @FilmReleaseDate
    private LocalDate releaseDate;

    @PositiveOrZero
    private int duration;

    public Film(String name, String description, LocalDate releaseDate, int duration) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}
