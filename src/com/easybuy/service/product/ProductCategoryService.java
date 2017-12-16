package com.easybuy.service.product;

import com.easybuy.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    public List<ProductCategory> querAllProductCategories(String parentId);
}
