package com.john.web;

import com.john.pojo.Book;
import com.john.pojo.Page;
import com.john.service.BookService;
import com.john.service.impl.BookServiceImpl;
import com.john.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author John
 * @create 2021-09-2821:32
 */
public class ClientBookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtil.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtil.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = this.bookService.page(pageNo, pageSize);
        page.setUrl("Client/bookServlet?action=page");
        req.setAttribute("page", page);
        System.out.println(page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtil.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtil.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtil.parseInt(req.getParameter("min"), 0);
        int max = WebUtil.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        Page<Book> page = this.bookService.pageByPrice(pageNo, pageSize, min, max);
        StringBuilder sb = new StringBuilder("Client/bookServlet?action=pageByPrice");
        if (req.getParameter("min" )!= null){
            sb.append("&min=").append(min);
        }
        if (req.getParameter("max" )!= null){
            sb.append("&max=").append(max);
        }
        page.setUrl(sb.toString());
        req.setAttribute("page", page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);

    }
}
