package com.john.web;

import com.google.gson.Gson;
import com.john.pojo.Book;
import com.john.pojo.Cart;
import com.john.pojo.CartItem;
import com.john.service.BookService;
import com.john.service.impl.BookServiceImpl;
import com.john.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author John
 * @create 2021-09-2722:30
 */
public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("adsa");
//
//        System.out.println("商品编号"+ req.getParameter("id"));
        int id = WebUtil.parseInt(req.getParameter("id"), 0);

        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
         cart = new Cart();
         req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        req.getSession().setAttribute("lastName", cartItem.getName());
        resp.sendRedirect(req.getHeader("Referer"));

        // 1.获取商品编号

        // 2.调用bookService.queryBookById().Book;

        // 3.把book对象转换成为CartItem

        // 4.获取Session中的购物车对象cart

        // 5.调用cart.addItem();添加商品选项

        //6.返回购物车总的商品数量和最后一个添加商品的名称

    }
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("adsa");
//
//        System.out.println("商品编号"+ req.getParameter("id"));
        int id = WebUtil.parseInt(req.getParameter("id"), 0);

        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        req.getSession().setAttribute("lastName", cartItem.getName());
//        resp.sendRedirect(req.getHeader("Referer"));

        // 1.获取商品编号

        // 2.调用bookService.queryBookById().Book;

        // 3.把book对象转换成为CartItem

        // 4.获取Session中的购物车对象cart

        // 5.调用cart.addItem();添加商品选项

        //6.返回购物车总的商品数量和最后一个添加商品的名称
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalCount", cart.getTotalPrice());
        resultMap.put("lastname", cartItem.getName());
        Gson gson = new Gson();
        String resultMapString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapString);

    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtil.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null){
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtil.parseInt(req.getParameter("id"), 0);
        int count = WebUtil.parseInt(req.getParameter("count"), 1);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            cart.updateCount(id, count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
