package com.john.dao;

import com.john.dao.impl.BaseDao;
import com.john.pojo.Order;

/**
 * @author John
 * @create 2021-09-2920:51
 */
public interface OrderDao  {
    public int saveOrder(Order order);
}
