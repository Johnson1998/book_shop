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
     * ע��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.����session���û���¼����Ϣ��������Session��
        req.getSession().invalidate();
//        2.�ض�����ҳ
        resp.sendRedirect(req.getContextPath());
    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUSer = userService.login(new User(null, username, password, null));
        if (this.userService.login(new User((Integer)null, username, password, (String)null)) == null) {
            req.setAttribute("msg", "�û������������!");
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
                System.out.println("�û���[" + username + "]�Ѵ���");
                req.setAttribute("msg", "�û��� " + username + " �Ѵ���");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);


            } else {

                this.userService.registerUser(new User((Integer)null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "��֤����󣡣���");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("��֤��[" + code + "]����");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }
}

