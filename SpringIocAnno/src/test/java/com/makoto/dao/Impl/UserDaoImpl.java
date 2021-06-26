package com.makoto.dao.Impl;

import com.makoto.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


//<bean id="userDao" class="com.makoto.dao.Impl.UserDaoImpl"></bean>
//@Component("UserDao")
@Repository("UserDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("save---");
    }
}
