package com.easybuy.web;

import com.easybuy.entity.ProductCategory;
import com.easybuy.service.product.ProductCateforyServiceImpl;
import com.easybuy.service.product.ProductCategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = {"/Home"},name = "Home")
public class HomeServlet extends AbstractServlet {
    private ProductCategoryService productCategoryService;


    @Override
    public void init() throws ServletException {
        super.init();
        //实例化一些工作
        productCategoryService = new ProductCateforyServiceImpl();
    }

    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           // System.out.println("sssssssss");
            productCategoryService = new ProductCateforyServiceImpl();
            List<ProductCategory> pcList = productCategoryService.querAllProductCategories("0");
            request.setAttribute("pclist",pcList);
            request.getRequestDispatcher("/show/index.jsp").forward(request,response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
        }*/
    public String index(HttpServletRequest request, HttpServletResponse response)throws Exception {
        List<ProductCategory> pcList = productCategoryService.querAllProductCategories("0");
        request.setAttribute("pclist",pcList);
        //request.getRequestDispatcher("/show/index.jsp").forward(request,response);
        return "/show/index";
    }

    @Override
    public Class getServletClass() {
        return HomeServlet.class;
    }
}
