package com.john.dao.impl;

import com.john.dao.OrderDao;
import com.john.pojo.Order;

import java.util.List;

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

    @Override
    public List<Order> queryOrders() {
    String sql = "SELECT `order_id` orderId, `create_time` createTime, `price`,`status`,`user_id` userId  FROM t_order";
        return queryForList(Order.class, sql, new Object[0]);
    }

    @Override
    public void changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set `status` = ? where `order_id` = ?";
        update(sql, status, orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(int userId){
        String sql  = "SELECT `order_id` orderId, `create_time` createTime, `price`, `status` FROM t_order WHERE " +
                "`user_id` = ?";
        return queryForList(Order.class, sql, userId);
    }

}
