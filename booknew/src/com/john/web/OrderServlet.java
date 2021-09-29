package com.john.web;

import com.john.pojo.Cart;
import com.john.pojo.User;
import com.john.service.OrderService;
import com.john.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author John
 * @create 2021-09-2922:18
 */
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    /**
     * ���ɶ���
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer id = loginUser.getId();
        String orderId = orderService.createOrder(cart, id);
        req.getSession().setAttribute("orderId", orderId);
//        req.setAttribute("orderId", orderId);
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);

        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}