package com.john.dao;

import com.john.pojo.User;

/**
 * @author John
 * @create 2021-09-269:28
 */
public interface UserDao {
    User queryUserByUsername(String var1);

    User queryUserByUsernameAndPassword(String var1, String var2);

    int saveUser(User var1);
}
