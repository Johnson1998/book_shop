package com.john.test;

import com.john.service.UserService;
import com.john.service.impl.UserServiceImpl;
import com.mysql.cj.jdbc.admin.MiniAdmin;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author John
 * @create 2021-10-0910:11
 */
public class UserServiceImplTest {

    @Test
    public void isUsernameEqualEmail() {
        UserService userService = new UserServiceImpl();
        System.out.println(userService.isUsernameEqualEmail("admin", "644128256@qq.com"));
    }
}