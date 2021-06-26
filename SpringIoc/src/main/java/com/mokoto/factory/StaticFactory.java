package com.mokoto.factory;

import com.mokoto.Impl.UserDaoImpl;
import com.mokoto.UserDao;

public class StaticFactory {

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }
}
