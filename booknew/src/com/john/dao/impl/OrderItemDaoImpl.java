package com.john.dao.impl;

import com.john.dao.OrderItemDao;
import com.john.pojo.OrderItem;

import java.util.List;

/**
 * @author John
 * @create 2021-09-2920:55
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`, `count`, `price`, `total_price`, `order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(),
                orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "SELECT `id`,`name`,`count`,`price`,`total_price` totalPrice, `order_id` orderId FROM " +
                "t_order_item WHERE order_id = ?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
