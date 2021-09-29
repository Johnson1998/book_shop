package com.john.test;

import com.john.pojo.Cart;
import com.john.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author John
 * @create 2021-09-2721:49
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"dsadsa", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"sa", 1, new BigDecimal(1000), new BigDecimal(100)));
        cart.addItem(new CartItem(1,"ssa", 1, new BigDecimal(1000), new BigDecimal(1000)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"dsadsa", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"sa", 1, new BigDecimal(1000), new BigDecimal(100)));
        cart.addItem(new CartItem(2,"ssa", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"dsadsa", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"sa", 1, new BigDecimal(1000), new BigDecimal(100)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"dsadsa", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"sa", 1, new BigDecimal(1000), new BigDecimal(100)));
        cart.addItem(new CartItem(2,"ssa", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.updateCount(1, 33);
        System.out.println(cart);
    }
}