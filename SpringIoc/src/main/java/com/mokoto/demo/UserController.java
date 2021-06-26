package com.mokoto.demo;

import com.mokoto.service.Impl.UserServiceImpl;
import com.mokoto.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        //UserService userService = (UserService) app.getBean("UserService");
        UserService userService = app.getBean(UserService.class);
        userService.save();
        /*UserService userService = new UserServiceImpl();
        userService.save();*/
    }
}
