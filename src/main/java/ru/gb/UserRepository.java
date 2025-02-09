package ru.gb;


import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component // Аннотация Spring для создания бина
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final List<User> users;

    // Внедряем JdbcTemplate через конструктор
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.users = new ArrayList<>();

        users.add(new User(1, "Igor"));
        users.add(new User(2, "User #2"));
        users.add(new User(3, "User #3"));
        users.add(new User(4, "User #4"));
        users.add(new User(5, "User #5"));
    }

    public List<User> getAll() {
        return List.copyOf(users);
    }

    public Optional<User> getById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public User getByName(String name) {
        return users.stream()
                .filter(user -> Objects.equals(user.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        int rowsEffected = jdbcTemplate.update(sql, id);
        if (rowsEffected > 0) {
            System.out.println(" Данные удалены! ID: " + id);
        } else {
            System.out.println(" Данные не найдены");
        }
    }

    public User updateUser(User user) {
        String sql = "UPDATE users SET name = ? WHERE id = ?";
        int rowsEffected = jdbcTemplate.update(sql, user.getName(), user.getId());

        if (rowsEffected == 0) {
            throw new RuntimeException(" Пользователь не найден: " + user.getName());
        }
        return user;
    }

    public User update(User updateUser) {
        for (User user : users) {
            if (updateUser.getId() == user.getId()) {
                user.setName(updateUser.getName());
                return user;
            }
        }
        throw new RuntimeException(" Пользователь не найден");
    }
}




