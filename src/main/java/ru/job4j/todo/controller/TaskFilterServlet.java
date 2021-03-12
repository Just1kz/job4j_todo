package ru.job4j.todo.controller;

import com.google.gson.Gson;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.service.HbmToDo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TaskFilterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Item> list = HbmToDo.instOf().showFilter();
        String json = new Gson().toJson(list);
        resp.setContentType("json");
        resp.getWriter().write(json);
    }
}
