package com.john.test;

import com.john.dao.BookDao;
import com.john.dao.impl.BookDaoImpl;
import com.john.pojo.Book;
import com.john.pojo.Page;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author John
 * @create 2021-09-299:17
 */
public class BookDaoImplTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(1, Page.PAGE_SIZE, 10, 50)) {
            System.out.println(book);
        }
    }
}