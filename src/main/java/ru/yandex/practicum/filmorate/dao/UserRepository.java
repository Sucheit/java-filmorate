package ru.yandex.practicum.filmorate.dao;

import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    private final Map<Long, User> users;

    private long id;

    public UserRepository() {
        users = new HashMap<>();
        id = 0;
    }

    private long generateId() {
        return ++id;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public User addUser(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        user.setId(generateId());
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    public User updateUser(User user) {
        if (!users.containsKey(user.getId())) {
            throw new RuntimeException("Пользователя с id = " + user.getId() + " не существует.");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        users.put(user.getId(), user);
        return user;
    }
}
