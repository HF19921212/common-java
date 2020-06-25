package com.brain.ums.mapper;

import com.brain.ums.domain.User;

public interface UserMapper {

    //查询用户
    User loadUserByUsername(String userName);
}
