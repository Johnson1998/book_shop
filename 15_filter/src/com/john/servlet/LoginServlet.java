package com.john.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author John
 * @create 2021-09-2622:04
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("john".equals(username)){
            req.getSession().setAttribute("user", username);
            resp.getWriter().write("µÇÂ¼³É¹¦");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
