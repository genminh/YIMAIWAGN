package com.easybuy.service.product;

import com.easybuy.entity.ProductCategory;
import com.easybuy.utils.ProductCategoryVo;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 根据parentID查询出所有商品的分类信息
     * @param parentId
     * @return
     */
    public List<ProductCategory> querAllProductCategories(String parentId);

    /**
     * 查询出所有的分类
     * @return
     */
    public List<ProductCategoryVo> querAllProductCategories();
}
