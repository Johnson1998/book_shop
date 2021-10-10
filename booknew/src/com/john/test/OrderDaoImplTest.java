package com.john.test;

import com.john.dao.OrderDao;
import com.john.dao.impl.OrderDaoImpl;
import com.john.pojo.Order;
import org.junit.Test;

import javax.xml.crypto.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author John
 * @create 2021-09-2921:26
 */
public class OrderDaoImplTest {

    @Test
    public void saveOrder() {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234", "new Date()", new BigDecimal(100),0 ,1));

    }
    @Test
    public void queryOrders() {
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.queryOrders());
    }

    @Test
    public void changeOrderStatus(){
        OrderDaoImpl orderDao = new OrderDaoImpl();
        Integer test = 2;
        orderDao.changeOrderStatus("163384845801433", test);
    }
}