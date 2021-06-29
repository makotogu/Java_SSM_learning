package makoto.mapper;

import makoto.domain.SysUser;
import makoto.domain.User;

import java.util.List;

public interface SysUserMapper {
    public List<SysUser> findUserAndRole();
}
