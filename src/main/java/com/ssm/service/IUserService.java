package com.ssm.service;

import com.ssm.model.People;
import com.ssm.model.User;

public interface IUserService {
    User selectUser(int userId);
    People selectPeople(int peopleId);
}
