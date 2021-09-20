package com.john.dao.impl;

import com.john.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author John
 * @create 2021-09-1915:59
 */
public abstract class BaseDao {

//    使用DBUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update()方法用来执行,Insert\Update\Delete语句
     * 返回-1代表失败
     * @return
     */
    public int update(String sql, Object ...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection);
        }
        return -1;
    }

    /**
     * 查询返回多个javaBean的sql语句
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args  sql对应的参数值
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object ...args){
        Connection connection = JDBCUtils.getConnection();
        try {
           return queryRunner.query(connection, sql, new BeanHandler<T>(type),
                    args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection);
        }
    return null;
    }

    /**
     * 查询返回一个javaBean的sql语句
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args  sql对应的参数值
     * @return
     */
    public <T>List<T> queryForList(Class<T> type, String sql, Object ...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(type),
                    args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection);
        }
        return null;
    }
    
    public Object queryForSingleValue(String sql, Object ...args){
        Connection connection = JDBCUtils.getConnection();

        try {
            return queryRunner.query(connection, sql, new ScalarHandler(),
                    args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
