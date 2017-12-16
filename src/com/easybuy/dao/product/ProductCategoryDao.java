package com.easybuy.dao.product;

import com.easybuy.entity.ProductCategory;

import java.util.List;

/**
 * 查询所有的商品类信息
 */
public interface ProductCategoryDao {
     List<ProductCategory> querAllProductCategories(String parentId);
}
