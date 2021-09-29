package com.john.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author John
 * @create 2021-09-2521:55
 */
public class SessionServlet extends BaseServlet{
    protected void createOrGetSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        boolean isNew = session.isNew();
        String id = session.getId();
        resp.getWriter().write("得到Session，他的id是" + id + "<br>");
        resp.getWriter().write("这个session是否是新创建的" + isNew + "<br>");
    }

    /**
     * 在session域中存数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("key1", "value1");
        resp.getWriter().write("已经往session存数据");
    }
    protected void getAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object key1 = req.getSession().getAttribute("key1");
        resp.getWriter().write("从session中获取key1的数据是" + key1);

    }
}
