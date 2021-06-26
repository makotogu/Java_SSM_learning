package makoto.dao.impl;

import makoto.dao.RoleDao;
import makoto.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "roleDao")
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> findAll() {
        List<Role> roleList = jdbcTemplate.query("select * from sys_role", new BeanPropertyRowMapper<Role>(Role.class));
        return roleList;
    }

    @Override
    public void addRole(Role role) {
        jdbcTemplate.update("insert into sys_role values (? , ? , ?)", null, role.getRoleName(), role.getRoleDesc());
    }

    @Override
    public void deleteRole(Integer roleId) {
        jdbcTemplate.update("delete from sys_role where id = ?", roleId);
    }

    @Override
    public List<Role> findRoleByUserId(Long id) {
        List<Role> roleList = jdbcTemplate.query("select * from sys_user_role ur, sys_role r where ur.roleId = r.id and ur.userId = ?", new BeanPropertyRowMapper<Role>(Role.class), id);
        return roleList;
    }
}
