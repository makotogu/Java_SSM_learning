package makoto.mapper;

import makoto.domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> findAll();
}
