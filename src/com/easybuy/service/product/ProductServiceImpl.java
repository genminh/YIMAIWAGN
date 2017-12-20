package com.easybuy.service.product;

import com.easybuy.dao.product.ProductDao;
import com.easybuy.dao.product.ProductDaoImpl;
import com.easybuy.entity.Product;
import com.easybuy.params.ProductParams;
import com.easybuy.utils.DataSourceUtil;
import com.easybuy.utils.EmptyUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService  {
    private Connection connection;
    private ProductDao productDao;
    @Override
    public int queryProductCount(ProductParams params) {
        Integer num = 0;
        try {
            connection = DataSourceUtil.opeanConnection();
            productDao = new ProductDaoImpl(connection);
            num = productDao.queryProductCount(params);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
        return num;
    }

    @Override
    public List<Product> queryProductList(ProductParams params) {
        List<Product> productList = new ArrayList();
        try {
            connection = DataSourceUtil.opeanConnection();
            productDao = new ProductDaoImpl(connection);
            //设置查询参数
            System.out.println(params.getCategoryId().toString());
            productList = productDao.queryProductList(params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
        return productList;
    }
}
