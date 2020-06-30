package com.city.repository;

import com.city.model.SysMenus;

public interface SysMenusMapper {
    int deleteByPrimaryKey(Short id);

    int insert(SysMenus record);

    int insertSelective(SysMenus record);

    SysMenus selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(SysMenus record);

    int updateByPrimaryKey(SysMenus record);
}