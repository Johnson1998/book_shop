package com.john.service;

import com.john.project.Book;

import java.util.List;

/**
 * @author John
 * @create 2021-09-2122:53
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();
}
