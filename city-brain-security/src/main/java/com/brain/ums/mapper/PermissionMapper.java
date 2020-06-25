package com.brain.ums.mapper;

import com.brain.ums.domain.RolePermisson;

import java.util.List;

public interface PermissionMapper {

    //权限资源 和 角色对应的表  也就是 角色权限 中间表
    List<RolePermisson> getRolePermissions();
}
