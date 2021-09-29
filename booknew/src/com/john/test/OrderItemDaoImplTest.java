package com.john.test;

import com.john.dao.impl.OrderItemDaoImpl;
import com.john.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author John
 * @create 2021-09-2921:31
 */
public class OrderItemDaoImplTest {

    @Test
    public void saveOrderItem() {
        OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null, "eawew", 1, new BigDecimal(100), new BigDecimal(100), "1234"));
        orderItemDao.saveOrderItem(new OrderItem(null, "zcxzx", 2, new BigDecimal(100), new BigDecimal(100), "1234"));
        orderItemDao.saveOrderItem(new OrderItem(null, "123", 3, new BigDecimal(100), new BigDecimal(100), "1234"));
    }
}