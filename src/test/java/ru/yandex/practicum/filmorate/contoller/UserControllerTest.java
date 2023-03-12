package ru.yandex.practicum.filmorate.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    public void UserController_getAllUsers_thenReturnListOfUsers() {
        List<User> users = List.of(
                new User(1L, "name1@mail.com", "login1", "name1",
                        LocalDate.of(1995, 5, 3))
                , new User(2L, "name2@mail.com", "login2", "name2",
                        LocalDate.of(1994, 7, 5))
        );
        when(userRepository.getUsers()).thenReturn(users);
        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @SneakyThrows
    @Test
    public void addUser() {
        User userToAdd = new User("user@mail.com", "userLogin", "userName",
                LocalDate.of(1992, 10, 21));
        when(userRepository.addUser(userToAdd)).thenReturn(userToAdd);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userToAdd)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("user@mail.com"))
                .andExpect(jsonPath("$.login").value("userLogin"))
                .andExpect(jsonPath("$.name").value("userName"))
                .andExpect(jsonPath("$.birthday").value("1992-10-21"))
                .andReturn();
    }

    @SneakyThrows
    @Test
    public void updateUser() {
        User userToUpdate = new User(1, "user@mail.com", "userLogin", "userName",
                LocalDate.of(1992, 10, 21));
        when(userRepository.updateUser(userToUpdate)).thenThrow(UserNotFoundException.class);
        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userToUpdate)))
                .andExpect(status().is(404))
                .andReturn();
    }
}
