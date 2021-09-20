package john.dao;

import com.john.project.User;

/**
 * @author John
 * @create 2021-09-1922:55
 */
public interface UserDao {

    /**
     * 根据 用户名查询用户信息
     * @param Username 用户名
     * @return 返回null，说明没有这个用户。反之亦然
     */
    public User queryUserByUsername(String Username);

    /**
     * 根据用户名密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null，说明用户名或密码错误，反之亦然
     */
    public User queryUserByUsernameAndPassword(String username,
                                               String password);
    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);
}
