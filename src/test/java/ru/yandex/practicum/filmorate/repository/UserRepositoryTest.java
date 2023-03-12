package ru.yandex.practicum.filmorate.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.List;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        userRepository = new UserRepository();
        userRepository.addUser(new User(1, "user1@email.com", "login1", "name1", LocalDate.MIN));
        userRepository.addUser(new User(2, "user2@email.com", "login2", "name2", LocalDate.MIN));
    }

    @Test
    void testGetUsers() {
        List<User> users = userRepository.getUsers();
        Assertions.assertNotNull(users, "Не возвращает список пользователей");
        Assertions.assertEquals(2, users.size(), "Не верное кол-во пользователей");
    }

    @Test
    void testAddUser() {
        User newUser = new User(1, "user3@mail.com", "login", "", LocalDate.MIN);
        User addedUser = userRepository.addUser(newUser);
        Assertions.assertEquals(3, userRepository.getUsers().size(), "Не верное кол-во пользователей");
        Assertions.assertEquals(3, addedUser.getId(), "Не верно сгенерировался id");
        Assertions.assertEquals("login", addedUser.getName(), "Не верно установилось имя");
    }

    @Test
    void testUpdateUser() {
        User updatedUser = userRepository.updateUser(new User(2, "newEmail@email.com",
                "newLogin", "newName", LocalDate.of(2000, 1, 1)));
        Assertions.assertEquals("newEmail@email.com", updatedUser.getEmail()
                , "Не верно обновился емайл.");
        Assertions.assertEquals("newLogin", updatedUser.getLogin()
                , "Не верно обновился логин.");
        Assertions.assertEquals("newName", updatedUser.getName()
                , "Не верно обновилось имя.");
        Assertions.assertEquals(LocalDate.of(2000, 1, 1), updatedUser.getBirthday()
                , "Не верно обновилась дата рождения.");
        Assertions.assertThrows(RuntimeException.class, () -> userRepository.updateUser(new User(3,
                "newEmail@email.com", "newLogin", "newName",
                LocalDate.of(2000, 1, 1))), "Обновляется пользователь с неверным id.");
    }
}
