package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.service.UserLogin;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long id;

    @Email
    private String email;

    @UserLogin
    private String login;
    private String name;

    @Past
    private LocalDate birthday;

    public User(String email, String login, String name, LocalDate birthday) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }
}

