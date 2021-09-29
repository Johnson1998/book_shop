package com.john.servlet;

import com.john.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author John
 * @create 2021-09-2514:01
 */
public class CookieServlet extends BaseServlet{


    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1创建cooki对象
        Cookie cookie = new Cookie("key1", "value1");
        resp.addCookie(cookie);
        resp.getWriter().write("cookie创建成功");
    }
    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies){
            // getName方法返回cookie的key
            resp.getWriter().write("Cookie[" + cookie.getName() + "=" + cookie.getValue() + "] <br> /");
        }
    }
    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        方案一
//        1.先创建一个要修改的同名Cookie对象
//        2.在构造器，同时赋予新的cookie值
//        Cookie cookie = new Cookie("key1", "newValue1");

//        3.调用respond.addCookie(Cookie)
//        resp.addCookie(cookie);
//        resp.getWriter().write("key1的cookie值已经修改");


//        方案二：
//        1.先查找需要修改的Cookie对象
        Cookie cookie = CookieUtils.findCookie("key1", req.getCookies());
//        2.调用setValue（）方法赋予新的cookie值
        if (cookie != null) {
            cookie.setValue("newValue2");
//        3.调用respond。addcookie通知客户端保存
            resp.addCookie(cookie);
        }

    }
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie cookie = new Cookie("defaultLife", "defaultLife");
        cookie.setMaxAge(-1);
        resp.addCookie(cookie);
    }

}
