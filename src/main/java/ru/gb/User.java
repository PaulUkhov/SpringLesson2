package ru.gb;

import com.fasterxml.jackson.annotation.JsonCreator;

import org.springframework.stereotype.Component;



@Component
public class User {

    private static long idCounter = 1L;

    private final long id;
    private String name;
    // Конструктор без параметров для Spring
    public User() {
        this.id = idCounter++;
        this.name = "Default Name"; // Можно задать какое-то значение по умолчанию
    }

    // Конструктор с параметрами для десериализации из JSON
    @JsonCreator
    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Конструктор, который генерирует уникальный id
    public User(String name) {
        this.id = idCounter++; // Генерация уникального ID
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
