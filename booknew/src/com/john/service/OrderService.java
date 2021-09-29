package com.john.service;

import com.john.pojo.Cart;

/**
 * @author John
 * @create 2021-09-2920:03
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
