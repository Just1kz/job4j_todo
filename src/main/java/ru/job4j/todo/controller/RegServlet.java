package ru.job4j.todo.controller;

import ru.job4j.todo.service.HbmToDo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (HbmToDo.instOf().findByEmailAndPasswordUser(email, password) != null) {
            req.setAttribute("error", "Пользователь с указанным email уже зарегистрирован");
            req.getRequestDispatcher("reg.html").forward(req, resp);
        } else {
            HbmToDo.instOf().createUser(name, email, password);
            resp.sendRedirect(req.getContextPath() + "login.html");
        }
    }
}