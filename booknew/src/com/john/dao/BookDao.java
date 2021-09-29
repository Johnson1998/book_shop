package com.john.dao;


import com.john.pojo.Book;

import java.util.List;

/**
 * @author John
 * @create 2021-09-2610:06
 */
public interface BookDao {
    int addBook(Book var1);

    int deleteBookById(Integer var1);

    int updateBook(Book var1);

    Book queryBookById(Integer var1);

    List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int var1, int var2);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
