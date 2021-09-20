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
 * @create 2021-09-207:22
 */
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserSerivceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        2.检查验证码是否正确 暂时写死
        if ("abcd".equalsIgnoreCase(code)){
            //正确
//            3.检查用户名是否可用
            if (userService.existsUsername(username)){
                System.out.println("用户名[" + username + "]已存在");
//            跳到注册成功页面regist_success
//            不可用 跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.html").forward(req, resp);

            }else {
//                可用 调用service保存数据
                userService.registerUser(new User(null, username,password, email));
                req.getRequestDispatcher("/pages/user/regist_success.html").forward(req, resp);
            }
//            跳到注册成功页面regist_success
//            不可用 跳回注册页面

        }else{
            // 不正确 跳回注册页面
            System.out.println("验证码["+code+"]错误");
            req.getRequestDispatcher("/pages/user/regist.html").forward(req, resp);
        }
    }
}
