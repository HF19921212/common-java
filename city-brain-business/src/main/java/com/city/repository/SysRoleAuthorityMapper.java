package com.city.repository;

import com.city.model.SysRoleAuthority;

public interface SysRoleAuthorityMapper {
    int insert(SysRoleAuthority record);

    int insertSelective(SysRoleAuthority record);
}