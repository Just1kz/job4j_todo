package ru.job4j.todo.service;

import ru.job4j.todo.model.Item;

import java.util.List;

public interface TaskService {

    Item add(Item item);

    List<Item> findAll();

    List<Item> showFilter();

    boolean update(String key);
}
