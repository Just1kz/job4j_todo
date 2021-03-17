package ru.job4j.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.HbmToDo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(HbmToDo.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            User user = HbmToDo.instOf().findByEmailAndPasswordUser(email, password);
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            req.setAttribute("error", "Не верный email или password");
            req.getRequestDispatcher("login.html").forward(req, resp);
        }
    }
}
