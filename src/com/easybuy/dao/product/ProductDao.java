package com.easybuy.dao.product;

import com.easybuy.entity.Product;
import com.easybuy.params.ProductParams;

import java.util.List;

public interface ProductDao {
    /**
     * 根据条件查询商品列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<Product> queryProductList(ProductParams params)throws Exception;

    /**
     * 根据条件查询商品数量
     * @param params
     * @return
     * @throws Exception
     */
    public Integer queryProductCount(ProductParams params)throws Exception;
}
