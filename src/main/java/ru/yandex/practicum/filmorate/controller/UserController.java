package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.repository.UserRepository;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    public UserController() {
        userRepository = new UserRepository();
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        log.trace("Кол-во пользователей: " + userRepository.getUsers().size());
        return userRepository.getUsers();
    }

    @PostMapping(value = "/users")
    public User addUser(@Valid @RequestBody User user) {
        log.trace("Пользователь добавлен: " + user);
        return userRepository.addUser(user);
    }

    @PutMapping(value = "/users")
    public User updateUser(@Valid @RequestBody User user) {
        log.trace("Пользователь обновлен: " + user);
        return userRepository.updateUser(user);
    }
}
