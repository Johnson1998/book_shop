package com.john.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author John
 * @create 2021-09-269:32
 */
public class JDBCUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    public JDBCUtils() {
    }

    public static void main(String[] args) {
    }

    public static Connection getConnection() {
//        DruidPooledConnection conn = null;
        Connection conn = conns.get();
        if (conn == null){
            try {
                conn = dataSource.getConnection();
                conns.set(conn); //保存到ThreaLocal对象中，供后面的jdbc操作使用
                conn.setAutoCommit(false); //设置手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        try {
//            conn = dataSource.getConnection();
//        } catch (Exception var2) {
//            var2.printStackTrace();
//        }

        return conn;
    }

    /**
     * 提交事务并关闭连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection != null){ //不等于null说明使用过连接，操作过数据库

            try {
                connection.commit(); //提交事务

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        conns.remove(); //一定要使用remove操作，否则回出错（因为tomacat服务器底层使用了线程池技术）

        }
    }
    /**
     * 回滚事务并关闭连接
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection != null){ //不等于null说明使用过连接，操作过数据库

            try {
                connection.rollback(); //回滚事务

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            conns.remove(); //一定要使用remove操作，否则回出错（因为tomacat服务器底层使用了线程池技术）

        }
    }

//    public static void close(Connection conn) {
//        try {
//            conn.close();
//        } catch (Exception var2) {
//            var2.printStackTrace();
//        }
//
//    }

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            System.out.println(dataSource.getConnection());
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}