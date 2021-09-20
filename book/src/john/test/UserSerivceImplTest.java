package john.test;

import com.john.project.User;
import com.john.service.UserService;
import com.john.service.impl.UserSerivceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author John
 * @create 2021-09-207:05
 */
public class UserSerivceImplTest {
    UserService userService = new UserSerivceImpl();
    @Test
    public void registerUser() {
        userService.registerUser(new User(null, "john", "666666","66663@qq.com"));
        userService.registerUser(new User(null, "jdsfsn", "6dsfds666","66fsdf63@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "john", "666666", null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("john")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名不存在");
        }
    }
}