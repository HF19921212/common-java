package org.apache.dubbo.dto;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息
 */
public class EsProductDTO implements Serializable {
    private static final long serialVersionUID = -1L;
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
    private String productSn;
    private String name;
    private String detailTitle;
    private String detailSubTitle;
    private String keyword;
    private List<EsProductAttributeValueDTO> attrValueList;

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

    public List<EsProductAttributeValueDTO> getAttrValueList() {
        return attrValueList;
    }

    public void setAttrValueList(List<EsProductAttributeValueDTO> attrValueList) {
        this.attrValueList = attrValueList;
    }
}
