package com.city.repository;

import com.city.model.SysUsers;

public interface SysUsersMapper {
    int deleteByPrimaryKey(Short id);

    int insert(SysUsers record);

    int insertSelective(SysUsers record);

    SysUsers selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(SysUsers record);

    int updateByPrimaryKey(SysUsers record);

    /********************************* 自定义SQL *********************************/

    /**
     * 通过账号查用户信息
     * @param account
     * @return
     */
    SysUsers findByAccount(String account);

}