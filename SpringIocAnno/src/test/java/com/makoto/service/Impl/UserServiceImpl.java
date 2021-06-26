package com.makoto.service.Impl;

import com.makoto.dao.UserDao;
import com.makoto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

//<bean id="userService" class="com.makoto.service.Impl.UserServiceImpl">
//@Component("UserService")
@Service("UserService")
@Scope("prototype")
public class UserServiceImpl implements UserService {

    @Value("${jdbc.driver}")
    private String driver;

    //<property name="userDao" ref="UserDao"></property>
    //@Autowired //按照数据类型从Spring容器中进行匹配
    //@Qualifier("UserDao")//是按照id值从容器中进行匹配的，但是注意此处要结合Autowired一起使用
    @Resource(name="UserDao")
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println(driver);
        userDao.save();
    }

    @PostConstruct
    public void init() {
        System.out.println("初始");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁");
    }
}
