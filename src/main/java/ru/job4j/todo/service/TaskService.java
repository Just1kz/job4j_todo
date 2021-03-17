package ru.job4j.todo.service;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.List;

public interface TaskService {

    Item add(Item item);

    List<Item> findAll();

    List<Item> showFilter();

    void update(String key);

    User createUser(String name, String email, String password);

    User findByEmailAndPasswordUser(String email, String password);
}
