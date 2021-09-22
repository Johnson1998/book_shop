package com.john.web;

import com.john.project.User;
import com.john.service.UserService;
import com.john.service.impl.UserSerivceImpl;
import com.john.utils.WebUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author John
 * @create 2021-09-2114:08
 */
public class UserServlet extends BaseServlet {
    UserService userService= new UserSerivceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
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

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = (User) WebUtil.copyParamToBean(req.getParameterMap(), new User());

//        2.检查验证码是否正确 暂时写死
        if ("abcd".equalsIgnoreCase(code)){
            //正确
//            3.检查用户名是否可用
            if (userService.existsUsername(username)){
                System.out.println("用户名[" + username + "]已存在");
//            跳到注册成功页面regist_success
//            不可用 跳回注册页面
                req.setAttribute("msg", "用户名 "+username+" 已存在");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

            }else {

//                可用 调用service保存数据
                userService.registerUser(new User(null, username,password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
//            跳到注册成功页面regist_success
//            不可用 跳回注册页面

        }else{
            // 不正确 跳回注册页面
            req.setAttribute("msg", "验证码错误！！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码["+code+"]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }


}
