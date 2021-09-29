package com.john.test;

import com.john.pojo.Cart;
import com.john.pojo.CartItem;
import com.john.service.OrderService;
import com.john.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author John
 * @create 2021-09-2922:13
 */
public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"dsadsa", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"sa", 1, new BigDecimal(1000), new BigDecimal(100)));
        cart.addItem(new CartItem(1,"ssa", 1, new BigDecimal(1000), new BigDecimal(1000)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println("∂©µ•∫≈ «£∫" + orderService.createOrder(cart, 1));
    }
}