package com.john.service;

import com.john.pojo.User;

/**
 * @author John
 * @create 2021-09-269:44
 */
public interface UserService {
    void registerUser(User var1);

    User login(User var1);

    boolean existsUsername(String var1);
}