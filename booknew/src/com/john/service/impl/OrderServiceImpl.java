package com.john.service.impl;

import com.john.dao.BookDao;
import com.john.dao.OrderDao;
import com.john.dao.OrderItemDao;
import com.john.dao.impl.BookDaoImpl;
import com.john.dao.impl.OrderDaoImpl;
import com.john.dao.impl.OrderItemDaoImpl;
import com.john.pojo.*;
import com.john.service.OrderService;
import com.john.util.DateUtil;

import java.util.Date;
import java.util.List;
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
        // 订单号===唯一性 存在问题
        String orderId = System.currentTimeMillis() + "" +userId;
        // 创建一个订单对象
        Order order = new Order(orderId, DateUtil.DateToString(new Date()), cart.getTotalPrice(), 0, userId);
        // 保存订单
        orderDao.saveOrder(order);
        // 遍历购物车中每一个商品项转化成订单保存到数据库
//        int i = 12 /0;
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
           // 获取每一个商品项转化成为订单项保存到数据库
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),
                    cartItem.getTotalPrice(), orderId);
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

                //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount());
            book.setStock( book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);

        }

        cart.clear();



        return orderId;

    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public List<Order> queryOrderByUserId(int userId){
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }
}
