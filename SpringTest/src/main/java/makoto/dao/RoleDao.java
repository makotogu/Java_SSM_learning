package makoto.dao;

import makoto.domain.Role;

import java.util.List;

public interface RoleDao {

    /*查询所有角色*/
    List<Role> findAll();
    /*添加角色*/
    void addRole(Role role);

    void deleteRole(Integer roleId);

    List<Role> findRoleByUserId(Long id);
}
