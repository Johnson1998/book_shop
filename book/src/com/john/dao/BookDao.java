package com.john.dao;

import com.john.project.Book;

import java.util.List;

/**
 * @author John
 * @create 2021-09-2122:05
 */
public interface BookDao {

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();
}
