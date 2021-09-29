package com.john.dao.impl;

import com.john.dao.OrderItemDao;
import com.john.pojo.OrderItem;

/**
 * @author John
 * @create 2021-09-2920:55
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`, `count`, `price`, `total_price`, `order_id`) values(?,?,?," +
                "?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(),
                orderItem.getId());
    }
}
