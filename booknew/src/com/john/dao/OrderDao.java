package com.john.dao;

import com.john.dao.impl.BaseDao;
import com.john.pojo.Order;

import java.util.List;

/**
 * @author John
 * @create 2021-09-2920:51
 */
public interface OrderDao  {
    public int saveOrder(Order order);

    public List<Order> queryOrders();

    public void changeOrderStatus(String orderId, Integer status);

    public List<Order> queryOrderByUserId(int userId);
}
