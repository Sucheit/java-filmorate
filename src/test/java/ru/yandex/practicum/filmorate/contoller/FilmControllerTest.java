package ru.yandex.practicum.filmorate.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.repository.FilmRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmRepository filmRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    public void getAllFilms() {
        List<Film> films = List.of(
                new Film(1, "name1", "description1", LocalDate.now(), 100)
                , new Film(2, "name2", "description2", LocalDate.now(), 120)
        );
        when(filmRepository.getFilms()).thenReturn(films);
        mockMvc.perform(get("/films")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @SneakyThrows
    @Test
    public void addFilm() {
        Film filmToAdd = new Film("name3", "description3", LocalDate.now(), 110);
        when(filmRepository.addFilm(filmToAdd)).thenReturn(filmToAdd);
        mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmToAdd)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn();
    }

    @SneakyThrows
    @Test
    public void updateFilm() {
        Film filmToUpdate = new Film(1, "name3", "description3", LocalDate.now(), 110);
        when(filmRepository.updateFilm(filmToUpdate)).thenReturn(filmToUpdate);
        mockMvc.perform(put("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmToUpdate)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
