package com.brain.ums.mapper;

import com.brain.ums.domain.Role;

import java.util.List;

public interface RoleMapper {

    //查询用户角色
    List<Role> getRolesByUserId(Long id);

}
