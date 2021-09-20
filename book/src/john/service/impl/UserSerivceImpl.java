package john.service.impl;

import com.john.dao.UserDao;
import com.john.dao.impl.UserDaoImpl;
import com.john.project.User;
import com.john.service.UserService;

/**
 * @author John
 * @create 2021-09-1923:47
 */
public class UserSerivceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null){
//            等于null代表没查到，没查到代表可用
            return false;
        }
        return true;
    }
}
