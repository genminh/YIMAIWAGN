package com.easybuy.service.product;

import com.easybuy.dao.product.ProductCategoryDao;
import com.easybuy.dao.product.ProductCategoryDaoImpl;
import com.easybuy.entity.ProductCategory;
import com.easybuy.utils.DataSourceUtil;
import com.easybuy.utils.ProductCategoryVo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCateforyServiceImpl implements ProductCategoryService{
    List<ProductCategoryVo> pc1VoList;
    List<ProductCategoryVo> pc2VoList ;
    List<ProductCategoryVo> pc3VoList;
    //成员变量
    private Connection connection;
    private ProductCategoryDao productCategoryDao;
    @Override
    public List<ProductCategory> querAllProductCategories(String parentId) {
        List<ProductCategory> pcList = new ArrayList<>();

        try {
            connection = DataSourceUtil.opeanConnection();
            productCategoryDao = new ProductCategoryDaoImpl(connection);
            if (null == parentId || "".equals(parentId)){
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
            DataSourceUtil.closeConnection(connection);
        }
        return pcList;
    }

    @Override
    public List<ProductCategoryVo> querAllProductCategories() {
       //查询一级分类的列表
        pc1VoList = new ArrayList<>();
        //查询所有的一级分类
        //parstId为0的商品就是一级分类
        List<ProductCategory> pc1List = querAllProductCategories(null);
        //查询二级分类
        for (ProductCategory productCategory1 : pc1List){
            //封装一级类
            ProductCategoryVo pc1Vo = new ProductCategoryVo();
            pc1Vo.setProductCategory(productCategory1);
            //查询二级分类VO列表
            pc2VoList = new ArrayList<>();
            //根据一级分类查询二级分类
            List<ProductCategory> pc2List = querAllProductCategories(productCategory1.getId().toString());
            for (ProductCategory productCategories2 : pc2List){
                ProductCategoryVo pc2Vo = new ProductCategoryVo();
                pc2VoList.add(pc2Vo);
                pc2Vo.setProductCategory(productCategories2);
                //三级分类的list  VO列表
                pc3VoList = new ArrayList<>();
                pc2Vo.setProductCategoryVosList(pc3VoList);
                //构建第三级
                //查询三级分类
                List<ProductCategory> pc3List = querAllProductCategories(productCategories2.getId().toString());
                for (ProductCategory productCategory3 : pc3List){
                    ProductCategoryVo pc3Vo = new ProductCategoryVo();
                    pc3Vo.setProductCategory(productCategory3);
                    pc3VoList.add(pc3Vo);
                }
            }
            pc1Vo.setProductCategoryVosList(pc2VoList);
            pc1VoList.add(pc1Vo);
        }
        for (int i = 0; i <pc2VoList.size() ; i++) {
            System.out.println(pc2VoList.get(i).getProductCategory().getId());
        }
        return pc1VoList;
    }
}
