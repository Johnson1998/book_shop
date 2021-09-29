package com.john.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.john.pojo.User;
import com.john.service.UserService;
import com.john.service.impl.UserServiceImpl;
import com.john.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author John
 * @create 2021-09-2610:01
 */
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    public UserServlet() {
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.销毁session中用户登录的信息（或销毁Session）
        req.getSession().invalidate();
//        2.重定向首页
        resp.sendRedirect(req.getContextPath());
    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUSer = userService.login(new User(null, username, password, null));
        if (this.userService.login(new User((Integer)null, username, password, (String)null)) == null) {
            req.setAttribute("msg", "用户名或密码错误!");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", loginUSer);
            System.out.println(req.getSession());
            System.out.println(req.getSession());
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        User user = (User) WebUtil.copyParamToBean(req.getParameterMap(), new User());
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if (token != null && token.equalsIgnoreCase(code)) {
            if (this.userService.existsUsername(username)) {
                System.out.println("用户名[" + username + "]已存在");
                req.setAttribute("msg", "用户名 " + username + " 已存在");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);


            } else {

                this.userService.registerUser(new User((Integer)null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误！！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }
}

