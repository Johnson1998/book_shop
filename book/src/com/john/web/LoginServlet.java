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
          //                把错误信息，和回显的表单项信息保存到Request域中
          req.setAttribute("msg", "用户名或密码错误!");
          req.setAttribute("username", username);

          req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
      }else {
          req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
      }

    }
}
