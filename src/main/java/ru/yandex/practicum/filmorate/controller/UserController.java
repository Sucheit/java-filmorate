package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dao.UserRepository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.ValidationService;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    public UserController() {
        userRepository = new UserRepository();
    }

    @GetMapping("/users")
    public List<User> findAll() {
        log.trace("Кол-во пользователей равно " + userRepository.getUsers().size());
        return userRepository.getUsers();
    }

    @PostMapping(value = "/users")
    public User create(@RequestBody User user) {
        ValidationService.validateUser(user);
        log.trace("Пользователь добавлен: " + user);
        return userRepository.addUser(user);
    }

    @PutMapping(value = "/users")
    public User update(@RequestBody User user) {
        ValidationService.validateUser(user);
        log.trace("Пользователь обновлен: " + user);
        return userRepository.updateUser(user);
    }
}
