package com.easybuy.service.product;

import com.easybuy.dao.product.ProductCategoryDao;
import com.easybuy.dao.product.ProductCategoryDaoImpl;
import com.easybuy.entity.ProductCategory;
import com.easybuy.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCateforyServiceImpl implements ProductCategoryService{
    //成员变量
    private Connection connection;
    private ProductCategoryDao productCategoryDao;
    @Override
    public List<ProductCategory> querAllProductCategories(String parentId) {
        List<ProductCategory> pcList = new ArrayList<>();

        try {
            connection = DataSourceUtil.opeanConnection();
            productCategoryDao = new ProductCategoryDaoImpl(connection);
            if (null != parentId || "".equals(parentId)){
                parentId = "0";
            }
            //进行查询
            pcList = productCategoryDao.querAllProductCategories(parentId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放connection
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return pcList;
    }
}
