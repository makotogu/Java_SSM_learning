package makoto.service.impl;

import makoto.dao.RoleDao;
import makoto.dao.UserDao;
import makoto.domain.Role;
import makoto.domain.User;
import makoto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<User> list() {
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            Long id = user.getId();
            List<Role> roles = roleDao.findRoleByUserId(id);
            user.setRoles(roles);
        }
        return userList;
    }

    @Override
    public void addUser(User user, Long[] roleIds) {
        Long userId = userDao.addUser(user);
        userDao.addRoleByUserId(userId, roleIds);
    }

    @Override
    public void delete(Long userId) {
        //删除关系表
        userDao.deleteUserRoleRel(userId);
        //删除用户表
        userDao.deleteUser(userId);
    }

    @Override
    public User login(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);
        return user;
    }
}
