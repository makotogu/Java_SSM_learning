package makoto.service;

import makoto.domain.User;

import java.util.List;

public interface UserService {
    List<User> list();

    void addUser(User user , Long[] roleIds);

    void delete(Long userId);

    User login(String username, String password);
}
