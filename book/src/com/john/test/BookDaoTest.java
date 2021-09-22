package com.john.test;

import com.john.dao.BookDao;
import com.john.dao.impl.BookDaoImpl;
import com.john.project.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author John
 * @create 2021-09-2122:27
 */
public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"john","johns",new BigDecimal(1), 1,1,null));
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(41,"john321","johns",new BigDecimal(1), 1000,1,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(41));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookDao.queryBooks());
    }
}