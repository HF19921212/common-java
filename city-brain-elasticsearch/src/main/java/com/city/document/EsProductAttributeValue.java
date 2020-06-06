package com.city.document;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 商品属性
 */
public class EsProductAttributeValue implements Serializable {
    private static final long serialVersionUID = 1L;
    //属性值id
    private Long id;
    //属性id
    private Long productAttributeId;
    //属性值
    @Field(type = FieldType.Keyword)
    private String value;
    @Field(type = FieldType.Keyword)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductAttributeId() {
        return productAttributeId;
    }

    public void setProductAttributeId(Long productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}