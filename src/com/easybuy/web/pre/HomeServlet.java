package com.easybuy.web.pre;

import com.easybuy.entity.News;
import com.easybuy.entity.ProductCategory;
import com.easybuy.service.news.NewsService;
import com.easybuy.service.news.NewsServiceImpl;
import com.easybuy.service.product.ProductCateforyServiceImpl;
import com.easybuy.service.product.ProductCategoryService;
import com.easybuy.utils.ProductCategoryVo;
import com.easybuy.web.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/Home"},name = "Home")
public class HomeServlet extends AbstractServlet {
    private ProductCategoryService productCategoryService;
    private NewsService newsService;
    @Override
    public void init() throws ServletException {
        //实例化一些工作
        productCategoryService = new ProductCateforyServiceImpl();
        newsService = new NewsServiceImpl();
    }
    public String index(HttpServletRequest request, HttpServletResponse response)throws Exception {
        //List<ProductCategory> pcList = productCategoryService.querAllProductCategories("0");
        List<ProductCategoryVo> pcList = productCategoryService.querAllProductCategories();
        List<News> newsList = newsService.queryAllNews();
        request.setAttribute("pclist",pcList);
        request.setAttribute("newsList",newsList);
        return "/show/index";
    }

    @Override
    public Class getServletClass() {
        return HomeServlet.class;
    }
}
