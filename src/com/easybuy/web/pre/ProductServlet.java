package com.easybuy.web.pre;

import com.easybuy.entity.Product;
import com.easybuy.params.ProductParams;
import com.easybuy.service.product.ProductCateforyServiceImpl;
import com.easybuy.service.product.ProductCategoryService;
import com.easybuy.service.product.ProductService;
import com.easybuy.service.product.ProductServiceImpl;
import com.easybuy.utils.EmptyUtils;
import com.easybuy.utils.Pager;
import com.easybuy.utils.ProductCategoryVo;
import com.easybuy.web.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Product",urlPatterns = {"/Product"})
public class ProductServlet extends AbstractServlet {
    private ProductService productService;
    private ProductCategoryService productCategoryService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
        productCategoryService = new ProductCateforyServiceImpl();
    }

    @Override
    public Class getServletClass() {
        return ProductServlet.class;
    }

    public  String queryProductList(HttpServletRequest request, HttpServletResponse response)throws Exception {
        String categoryId = request.getParameter("categoryId");
        //页面大小
        String pageSizeStr = request.getParameter("pageSize");
        //第几页
        String currentPageStr = request.getParameter("currentPage");
        String keyWord = request.getParameter("keyWord");
        int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8:Integer.parseInt(pageSizeStr);
        int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
        ProductParams params = new ProductParams();
        params.setCategoryId(EmptyUtils.isEmpty(categoryId)? null :Integer.parseInt(categoryId));
        params.setKeyword(keyWord);
        params.openPage((currentPage-1)*rowPerPage,rowPerPage);
        //分页
        int total = productService.queryProductCount(params);
        Pager pager = new Pager(total,rowPerPage,currentPage);
        pager.setUrl("/Product?action=queryProductList&categoryId="+ (EmptyUtils.isEmpty(categoryId)? "" :categoryId) + "&keyWord =" +(EmptyUtils.isEmpty(keyWord)? "" :categoryId));
        List<ProductCategoryVo> productCategoryVoList = productCategoryService.querAllProductCategories();
        List<Product> productList = productService.queryProductList(params);
        HttpSession session = request.getSession();
        session.setAttribute("productList", productList);
        session.setAttribute("pager", pager);
        session.setAttribute("total", total);
        System.out.println(total);
        session.setAttribute("keyWord", keyWord);
        session.setAttribute("pclist", productCategoryVoList);
        return "/show/queryProductList";
    }

}
