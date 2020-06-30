package com.city.repository;

import com.city.model.SysAuthoritys;

public interface SysAuthoritysMapper {
    int deleteByPrimaryKey(Short id);

    int insert(SysAuthoritys record);

    int insertSelective(SysAuthoritys record);

    SysAuthoritys selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(SysAuthoritys record);

    int updateByPrimaryKey(SysAuthoritys record);
}