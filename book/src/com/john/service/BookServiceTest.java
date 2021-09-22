package com.john.service;

import com.john.project.Book;
import com.john.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * @author John
 * @create 2021-09-229:40
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"怕怕怕怕", "1123", new BigDecimal(12313), 1321,21312,null));
    }

    @Test
    public void deleteBookById() {
//        for (int i = 37; i < 42; i++) {
//        bookService.deleteBookById(i);
//
//        }
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
    }
}