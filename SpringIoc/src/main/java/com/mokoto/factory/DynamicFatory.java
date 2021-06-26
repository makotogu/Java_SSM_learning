package com.mokoto.factory;

import com.mokoto.Impl.UserDaoImpl;
import com.mokoto.UserDao;

public class DynamicFatory {

    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

}
