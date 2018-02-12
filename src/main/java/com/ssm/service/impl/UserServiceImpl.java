package com.ssm.service.impl;

import com.ssm.dao.IUserDao;
import com.ssm.dao.PeopleMapper;
import com.ssm.model.People;
import com.ssm.model.User;
import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/***
 * @Date 2017/12/26
 *@Description UserServiceImpl
 * @author zhanghesheng
 * */
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public User selectUser(int userId) {
        return userDao.selectUser(userId);
    }

    @Override
   public People selectPeople(int peopleId) {
        return peopleMapper.selectByPrimaryKey(peopleId);
    }
}
