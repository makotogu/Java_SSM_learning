package com.makoto.test;

import com.mokoto.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    @Test
    /**
     * 测试scope属性
     */
    public void test1() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao1 = (UserDao) app.getBean("UserDao");
        //UserDao userDao2 = (UserDao) app.getBean("UserDao");
        System.out.println(userDao1);
        // System.out.println(userDao2);
        //((ClassPathXmlApplicationContext) app).close();
    }

}
