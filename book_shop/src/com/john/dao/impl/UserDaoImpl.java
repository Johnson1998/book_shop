package com.john.dao.impl;

import com.john.dao.UserDao;
import com.john.project.User;

/**
 * @author John
 * @create 2021-09-1923:04
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String Username) {
        String sql = "select id, username, password, email from t_user where username = ?";
        return queryForOne(User.class, sql, Username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id, username, password, email from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username, password, email)values(?, ?, ?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
