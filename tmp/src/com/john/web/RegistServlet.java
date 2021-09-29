package com.john.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author John
 * @create 2021-09-2619:08
 */
public class RegistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        // 删除验证码
        String code = req.getParameter("code");
        if (token != null && token.equalsIgnoreCase(code)){
            req.getRequestDispatcher("/regist_success.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/regist_fail.jsp").forward(req, resp);
        }

    }
}
