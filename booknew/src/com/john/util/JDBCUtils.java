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
                conns.set(conn); //���浽ThreaLocal�����У��������jdbc����ʹ��
                conn.setAutoCommit(false); //�����ֶ���������
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
     * �ύ���񲢹ر�����
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection != null){ //������null˵��ʹ�ù����ӣ����������ݿ�

            try {
                connection.commit(); //�ύ����

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        conns.remove(); //һ��Ҫʹ��remove����������س�����Ϊtomacat�������ײ�ʹ�����̳߳ؼ�����

        }
    }
    /**
     * �ع����񲢹ر�����
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection != null){ //������null˵��ʹ�ù����ӣ����������ݿ�

            try {
                connection.rollback(); //�ع�����

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            conns.remove(); //һ��Ҫʹ��remove����������س�����Ϊtomacat�������ײ�ʹ�����̳߳ؼ�����

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