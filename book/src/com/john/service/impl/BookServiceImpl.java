package com.john.service.impl;

import com.john.dao.BookDao;
import com.john.dao.impl.BookDaoImpl;
import com.john.project.Book;
import com.john.service.BookService;

import java.util.List;

/**
 * @author John
 * @create 2021-09-2122:55
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
}
