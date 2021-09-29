package com.john.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author John
 * @create 2021-09-269:32
 */
public class JDBCUtils {
    private static DruidDataSource dataSource;

    public JDBCUtils() {
    }

    public static void main(String[] args) {
    }

    public static Connection getConnection() {
        DruidPooledConnection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return conn;
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

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