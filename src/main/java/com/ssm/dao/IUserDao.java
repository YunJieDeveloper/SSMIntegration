package com.ssm.dao;

import com.ssm.model.User;
import org.springframework.stereotype.Repository;

/***
 * @Date 2017/12/26
 *@Description IUserDao 接口
 * @author zhanghesheng
 * */

public interface IUserDao {
    User selectUser(int id);
}
