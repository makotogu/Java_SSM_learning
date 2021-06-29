package makoto.mapper;

import makoto.domain.User;

import java.util.List;

public interface UserMapper {

    public void insertUser(User user);

    public User findById(Integer id);

    public List<User> findAllUsers();

}
