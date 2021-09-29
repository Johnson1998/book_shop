package com.john.service.impl;

import com.john.dao.BookDao;
import com.john.dao.OrderDao;
import com.john.dao.OrderItemDao;
import com.john.dao.impl.BookDaoImpl;
import com.john.dao.impl.OrderDaoImpl;
import com.john.dao.impl.OrderItemDaoImpl;
import com.john.pojo.*;
import com.john.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author John
 * @create 2021-09-2921:38
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        // ������===Ψһ��
        String orderId = System.currentTimeMillis() + "" +userId;
        // ����һ����������
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        // ���涩��
        orderDao.saveOrder(order);
        // �������ﳵ��ÿһ����Ʒ��ת���ɶ������浽���ݿ�

        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
           // ��ȡÿһ����Ʒ��ת����Ϊ������浽���ݿ�
            CartItem cartItem = entry.getValue();
            // ת��Ϊÿһ��������
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),
                    cartItem.getTotalPrice(), orderId);
            // ���涩������ݿ�
            orderItemDao.saveOrderItem(orderItem);

                //���¿�������
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount());
            book.setStock( book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);

        }

        cart.clear();

        //

        return orderId;

    }
}
