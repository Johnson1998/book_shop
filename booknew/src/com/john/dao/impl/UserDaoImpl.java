package com.john.dao.impl;

import com.john.pojo.User;

/**
 * @author John
 * @create 2021-09-269:40
 */
    public class UserDaoImpl extends BaseDao implements com.john.dao.UserDao {
        @Override
        public User queryUserByUsername(String Username) {
            String sql = "select id, username, password, email from t_user where username = ?";
            return (User)this.queryForOne(User.class, sql, new Object[]{Username});
        }

        @Override
        public User queryUserByUsernameAndPassword(String username, String password) {
            String sql = "select id, username, password, email from t_user where username = ? and password = ?";
            return (User)this.queryForOne(User.class, sql, new Object[]{username, password});
        }

        @Override
        public int saveUser(User user) {
            String sql = "insert into t_user(username, password, email)values(?, ?, ?)";
            return this.update(sql, new Object[]{user.getUsername(), user.getPassword(), user.getEmail()});
        }

    @Override
    public int updateUser(String username , String newPassword) {
        String sql = "update t_user set password = ? where username = ?";
        return this.update(sql, new Object[]{newPassword, username});
    }
}
