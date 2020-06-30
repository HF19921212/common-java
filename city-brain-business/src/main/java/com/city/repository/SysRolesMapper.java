package com.city.repository;

import com.city.model.SysRoles;

public interface SysRolesMapper {
    int deleteByPrimaryKey(Short id);

    int insert(SysRoles record);

    int insertSelective(SysRoles record);

    SysRoles selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(SysRoles record);

    int updateByPrimaryKey(SysRoles record);
}