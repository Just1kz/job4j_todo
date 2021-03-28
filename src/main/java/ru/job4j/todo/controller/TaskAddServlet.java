package ru.job4j.todo.controller;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.HbmToDo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class TaskAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        User user = (User) req.getSession().getAttribute("user");
        Item item = new Item(description, user);
        String[] idCategories = req.getParameterValues("idCategories[]");
        item = HbmToDo.instOf().addCategoriesInItem(item, idCategories);
        HbmToDo.instOf().addItemBD(item);
    }
}
