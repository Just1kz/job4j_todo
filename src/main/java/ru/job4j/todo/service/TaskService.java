package ru.job4j.todo.service;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.List;

public interface TaskService {

    Item addCategoriesInItem(Item item, String[] ids);

    Item addItemBD(Item item);

    List<Item> findAll();

    List<Item> showFilter();

    void update(String key);

    void createUser(User user);

    User findByEmailAndPasswordUser(User user);

    public Category findById(int id);

    public List<Category> AllCategories();
}
