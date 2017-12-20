package com.easybuy.utils;

import com.easybuy.entity.ProductCategory;

import java.util.List;

/**
 * 负责与前台数据的交互
 */
public class ProductCategoryVo {
    //存放二级分类
    private ProductCategory productCategory;
    //存放三级分类的信息
    private List<ProductCategoryVo> productCategoryVosList;

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductCategoryVo> getProductCategoryVosList() {
        return productCategoryVosList;
    }

    public void setProductCategoryVosList(List<ProductCategoryVo> productCategoryVosList) {
        this.productCategoryVosList = productCategoryVosList;
    }
}
