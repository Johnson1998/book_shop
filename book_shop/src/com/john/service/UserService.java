package com.john.service;

import com.john.project.User;

/**
 * @author John
 * @create 2021-09-1923:42
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录用户
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 检查 用户名是否可用
     * @param username
     * @return
     */
    public boolean existsUsername(String username);
}
