package com.john.dao.impl;

import com.john.dao.OrderDao;
import com.john.pojo.Order;

/**
 * @author John
 * @create 2021-09-2920:52
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`, `create_time`, `price`, `status`, `user_id`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(),
                order.getUserId());

    }
}
