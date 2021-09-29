package com.john.dao.impl;

import com.john.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author John
 * @create 2021-09-269:38
 */
public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();

    public BaseDao() {
    }

    public int update(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();

        try {
            int var4 = this.queryRunner.update(connection, sql, args);
            return var4;
        } catch (SQLException var8) {
            var8.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }

        return -1;
    }

    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();

        try {
            Object var5 = this.queryRunner.query(connection, sql, new BeanHandler(type), args);
            return (T) var5;
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }

        return null;
    }

    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();

        try {
            List var5 = (List)this.queryRunner.query(connection, sql, new BeanListHandler(type), args);
            return var5;
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }

        return null;
    }

    public Object queryForSingleValue(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();

        try {
            Object var4 = this.queryRunner.query(connection, sql, new ScalarHandler(), args);
            return var4;
        } catch (Exception var8) {
            var8.printStackTrace();
            var8.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }

        return null;
    }
}

