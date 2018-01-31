package com.ssm.service.impl;

import com.ssm.dao.IUserDao;
import com.ssm.model.User;
import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/***
 * @Date 2017/12/26
 *@Description UserServiceImpl
 * @author zhanghesheng
 * */
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User selectUser(int userId) {
        return userDao.selectUser(userId);
    }
}
