package makoto.service;

import makoto.domain.Role;

import java.util.List;

public interface RoleService {

    /*查询所有角色*/
    List<Role> list();
    /*添加角色*/
    void addRole(Role role);

    void deleteRole(Integer roleId);
}
