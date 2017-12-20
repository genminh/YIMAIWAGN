package com.easybuy.dao.product;

import com.easybuy.dao.BaseDaoImpl;
import com.easybuy.entity.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoImpl extends BaseDaoImpl implements ProductCategoryDao {
    public ProductCategoryDaoImpl(Connection connection){
        super(connection);
    }
    //查看数据库中的商品分类
    public ProductCategory tableToClass(ResultSet resultSet) throws SQLException {
        ProductCategory pc = new ProductCategory();
        pc.setId(resultSet.getInt("id"));
        pc.setName(resultSet.getString("name"));
        pc.setParentId(resultSet.getInt("parentId"));
        pc.setType(resultSet.getInt("type"));
        pc.setIconClass(resultSet.getString("iconClass"));
        return pc;
    }

    /**
     * 数据查询
     * @param parentId
     * @return
     */
    @Override
    public List<ProductCategory> querAllProductCategories(String parentId) {
        List<ProductCategory> pcList = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select id, name, parentId, type, iconClass from easybuy_product_category where 1=1");
        List<Object> params = new ArrayList<>();
       ResultSet resultSet = null;
        if (null != parentId && !"".equals(parentId)){
            sql.append(" and parentId = ?");
            params.add(parentId);
        }
        //查询数据库语句
        try {
            resultSet=this.executeQuery(sql.toString(),params.toArray());
            while (resultSet.next()){
                //List集合添加值
                ProductCategory pc =tableToClass(resultSet);
                pcList.add(pc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关流
            this.closeResource(resultSet);
            this.closeResource();
           // DataSourceUtil.closeConnection(resultSet,preparedStatement,connection);
        }
        return pcList;
    }
}
