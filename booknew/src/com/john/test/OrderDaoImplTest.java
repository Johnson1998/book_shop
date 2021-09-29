package com.john.test;

import com.john.dao.impl.OrderDaoImpl;
import com.john.pojo.Order;
import org.junit.Test;

import javax.xml.crypto.Data;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author John
 * @create 2021-09-2921:26
 */
public class OrderDaoImplTest {

    @Test
    public void saveOrder() {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234", new Date(), new BigDecimal(100),0 ,1));

    }
}