package makoto.dao;

import makoto.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserMapper {
    public List<User> findAll() throws IOException;
    public User findById(Integer id);
}
