package com.city.model;

import lombok.Data;

@Data
public class SysMenus {
    private Short id;

    private String nameCh;

    private String nameEn;

    private Short parentId;

    private Short orderId;

    private String menuAuth;

    private Short authId;

    private String imgUrl;

}