package com.john.service;

import com.john.pojo.Book;
import com.john.pojo.Page;

import java.util.List;

/**
 * @author John
 * @create 2021-09-2610:08
 */
public interface BookService {
    void addBook(Book var1);

    void deleteBookById(Integer var1);

    void updateBook(Book var1);

    Book queryBookById(Integer var1);

    List<Book> queryBooks();

    Page<Book> page(int var1, int var2);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
