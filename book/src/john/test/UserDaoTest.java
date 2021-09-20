package john.test;

import com.john.dao.UserDao;
import com.john.dao.impl.UserDaoImpl;
import com.john.project.User;
import org.junit.Test;


/**
 * @author John
 * @create 2021-09-1923:23
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        if (userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用！");
        }else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin", "admin")== null){
            System.out.println("用户名密码错误， 登录失败");
        }else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "admin1", "123456", "77777222@qq.com")));
    }
}