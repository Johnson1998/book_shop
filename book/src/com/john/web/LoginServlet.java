package com.john.web;

import com.john.project.User;
import com.john.service.UserService;
import com.john.service.impl.UserSerivceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author John
 * @create 2021-09-2012:46
 */
public class LoginServlet extends HttpServlet {
     UserService userService= new UserSerivceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

      if (userService.login(new User(null, username, password,null)) == null){
          req.getRequestDispatcher("/pages/user/login.html").forward(req, resp);
      }else {
          req.getRequestDispatcher("/pages/user/login_success.html").forward(req, resp);
      }

    }
}
