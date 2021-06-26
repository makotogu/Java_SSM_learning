package makoto.dao;

import makoto.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    //迪奥哟用完返回用户的数据库自动生成的id
    Long addUser(User user);

    void addRoleByUserId(Long id, Long[] roleIds);


    void deleteUserRoleRel(Long userId);

    void deleteUser(Long userId);

    User findByUsernameAndPassword(String username, String password);
}
