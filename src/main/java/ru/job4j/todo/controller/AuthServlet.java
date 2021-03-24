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
        User user = new User(email, password);
        User user2 = HbmToDo.instOf().findByEmailAndPasswordUser(user);
        if (user2 != null && user2.getPassword().equals(password)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user2);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("error", "Не верный email или password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
