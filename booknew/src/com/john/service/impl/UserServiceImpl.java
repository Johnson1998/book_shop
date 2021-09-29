package com.john.service.impl;

import com.john.dao.UserDao;
import com.john.dao.impl.UserDaoImpl;
import com.john.pojo.User;
import com.john.service.UserService;

/**
 * @author John
 * @create 2021-09-269:46
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        this.userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return this.userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        return this.userDao.queryUserByUsername(username) != null;
    }
}

