package com.city.elasticsearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品信息
 * @Document 作用在类，标记实体类为文档对象，一般有两个属性
 *  indexName：对应索引库名称
 *  type：对应在索引库中的类型
 *  shards：分片数量，默认5
 *  replicas：副本数量，默认1
 *
 * @Field 作用在成员变量，标记为文档的字段，并指定字段映射属性：
 *  type：字段类型，是枚举：FieldType，可以是text、long、short、date、integer、object等
 *  text：存储数据时候，会自动分词，并生成索引
 *  keyword：存储数据时候，不会分词建立索引
 *  Numerical：数值类型，分两类
 *  基本数据类型：long、interger、short、byte、double、float、half_float
 *  浮点数的高精度类型：scaled_float
 *  需要指定一个精度因子，比如10或100。elasticsearch会把真实值乘以这个因子后存储，取出时再还原。
 *  Date：日期类型
 *  elasticsearch可以对日期格式化为字符串存储，但是建议我们存储为毫秒值，存储为long，节省空间。
 *  index：是否索引，布尔类型，默认是true
 *  store：是否存储，布尔类型，默认是false
 *  analyzer：分词器名称，这里的ik_max_word即使用ik分词器
 */
@Document(indexName = "pms", type = "product", shards = 1, replicas = 0)
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;
    //@Id 作用在成员变量，标记一个字段作为id主键
    @Id
    private Long id;
    private Long brandId;
    private Long productCategoryId;
    private Long productAttributeCategoryId;
    private String unit;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal marketPrice;
    private String description;
    private BigDecimal stockTotal;
    private BigDecimal weight;
    @Field(type = FieldType.Keyword)
    private String productSn;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String name;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String detailTitle;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String detailSubTitle;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(BigDecimal stockTotal) {
        this.stockTotal = stockTotal;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public String getDetailSubTitle() {
        return detailSubTitle;
    }

    public void setDetailSubTitle(String detailSubTitle) {
        this.detailSubTitle = detailSubTitle;
    }

}
