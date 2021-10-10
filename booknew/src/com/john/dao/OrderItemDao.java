package com.john.dao;

import com.john.pojo.OrderItem;

import java.util.List;

/**
 * @author John
 * @create 2021-09-2920:53
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);

    public List<OrderItem> queryOrderItemByOrderId(String orderId);
}
