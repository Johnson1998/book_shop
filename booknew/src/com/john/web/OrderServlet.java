package com.john.web;

import com.john.pojo.Cart;
import com.john.pojo.Order;
import com.john.pojo.OrderItem;
import com.john.pojo.User;
import com.john.service.OrderService;
import com.john.service.impl.OrderServiceImpl;
import com.john.util.JDBCUtils;
import com.john.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author John
 * @create 2021-09-2922:18
 */
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
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
        String orderId = null;
        orderId = orderService.createOrder(cart, id);
        req.getSession().setAttribute("orderId", orderId);

        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 管理员查看所有订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<Order> orderList = orderService.showAllOrders();
        req.setAttribute("orderList", orderList);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * 发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
    orderService.sendOrder(req.getParameter("orderId"));
    resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String orderId =req.getParameter("orderId");

        List<OrderItem> orderItemList = orderService.queryOrderItemByOrderId(orderId);

        req.setAttribute("orderItemList", orderItemList);

        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
    }

    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        int userId = WebUtil.parseInt(req.getParameter("userId"), 0);
        List<Order> myOrdersList = orderService.queryOrderByUserId(userId);
        req.setAttribute("myOrdersList", myOrdersList);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }
    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

    }
}
