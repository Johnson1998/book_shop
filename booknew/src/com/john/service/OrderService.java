package com.john.service;

import com.john.pojo.Cart;
import com.john.pojo.Order;
import com.john.pojo.OrderItem;

import java.util.List;

/**
 * @author John
 * @create 2021-09-2920:03
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

    public List<Order> showAllOrders();

    public void sendOrder(String orderId);

    public List<Order> queryOrderByUserId(int userId);

    public List<OrderItem> queryOrderItemByOrderId(String orderId);
}
