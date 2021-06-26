package makoto.dao.impl;

import makoto.dao.UserDao;
import makoto.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        List<User> userList = jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public Long addUser(User user) {

        //创建PreparedStatementCreator
        Connection connection;
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //使用原始jdbc完成组件
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values (? , ? , ? , ? , ? )", PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1, null);
                preparedStatement.setObject(2, user.getUsername());
                preparedStatement.setObject(3, user.getEmail());
                preparedStatement.setObject(4, user.getPassword());
                preparedStatement.setObject(5, user.getPhoneNum());
                return preparedStatement;
            }
        };
        //keyHolder
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(creator, keyHolder);

        //获得生成的主键
        long userId = keyHolder.getKey().longValue();

        //jdbcTemplate.update("insert into sys_user values (? , ? , ? , ? , ? )",null,user.getUsername(), user.getEmail(), user.getPassword(), user.getPhoneNum());
        return userId;
    }

    @Override
    public void addRoleByUserId(Long id, Long[] roleIds) {
        for (long roleId : roleIds) {
            jdbcTemplate.update("insert into sys_user_role values (? , ? )", id, roleId);
        }
    }

    @Override
    public void deleteUserRoleRel(Long userId) {
        jdbcTemplate.update("delete from  sys_user_role where userId = ?", userId);
    }

    @Override
    public void deleteUser(Long userId) {
        jdbcTemplate.update("delete from sys_user where id = ?", userId);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try {
            User user = jdbcTemplate.queryForObject("select * from sys_user where username = ? and password = ?", new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (DataAccessException e) {
            return null;
        }

    }
}


