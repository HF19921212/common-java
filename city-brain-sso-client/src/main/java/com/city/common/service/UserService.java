package com.city.common.service;

import com.city.common.pojo.User;

public interface UserService {

    User getUserByToken(String token);

}