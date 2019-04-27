package com.han.service;

import com.han.bzo.UserObject;
import com.han.dao.UserInfoMapper;
import com.han.dao.UserPasswordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserService
{
    @Autowired
    UserInfoMapper userDao;
    @Autowired
    UserPasswordMapper passwordDao;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public int  add (UserObject user){
        userDao.insert(user.userInfo);
        user.userPassword.setUserId(user.userInfo.getId());
        passwordDao.insert(user.userPassword);
        return user.userInfo.getId();
    }
}
