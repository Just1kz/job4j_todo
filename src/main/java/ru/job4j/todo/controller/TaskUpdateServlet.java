package ru.job4j.todo.controller;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.service.HbmToDo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaskUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String description = req.getParameter("description");
        HbmToDo.instOf().update(description);
    }
}
