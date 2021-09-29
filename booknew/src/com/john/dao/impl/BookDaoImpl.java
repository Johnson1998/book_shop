package com.john.dao.impl;

import com.john.dao.BookDao;
import com.john.pojo.Book;

import java.util.List;

/**
 * @author John
 * @create 2021-09-2610:07
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    public BookDaoImpl() {
    }

    public int addBook(Book book) {
        String sql = "insert into t_book(name, author, price, sales, stock, img_path)values(?, ?, ?, ?, ?, ?)";
        return this.update(sql, new Object[]{book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath()});
    }

    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return this.update(sql, new Object[]{id});
    }

    public int updateBook(Book book) {
        String sql = "update t_book set name=?, author=?, price=?, sales=?, stock=?,img_path=? where id=?";
        return this.update(sql, new Object[]{book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId()});
    }

    public Book queryBookById(Integer id) {
        String sql = "select id, name, author, price, sales, stock, 'img_path' imgpath from t_book where id=?";
        return (Book)this.queryForOne(Book.class, sql, new Object[]{id});
    }

    public List<Book> queryBooks() {
        String sql = "select id, name, author, price, sales, stock, 'img_path' imgpath from t_book";
        return this.queryForList(Book.class, sql, new Object[0]);
    }

    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number)this.queryForSingleValue(sql, new Object[0]);
        return count.intValue();
    }

    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select id, name, author, price, sales, stock, 'img_path' imgpath from t_book limit ?,?";
        return this.queryForList(Book.class, sql, new Object[]{begin, pageSize});
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number)this.queryForSingleValue(sql, new Object[]{min, max});
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select id, name, author, price, sales, stock, 'img_path' imgpath " +
                "from t_book  where price between ? and ? order by price limit ?,?";
        return this.queryForList(Book.class, sql, new Object[]{min, max, begin, pageSize, });
    }
}

