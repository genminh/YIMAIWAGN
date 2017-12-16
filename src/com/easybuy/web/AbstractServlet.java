package com.easybuy.web;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.utils.EmptyUtils;
import com.easybuy.utils.ReturnResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@WebServlet(name = "AbstractServlet")
public abstract class AbstractServlet extends HttpServlet {
    //获取到继承这个类的子类的一个实例
    public abstract Class getServletClass();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //action对请求进行处理的servlet的方法名称
        String actiom = request.getParameter("action");
        Method method = null;
        Object result = null;
        if (EmptyUtils.isEmpty(actiom)){
            //为空的时候跳转回首页
            //如果没有拿到方法的名字，就直接返回首页
            request.getRequestDispatcher("/show/index.jsp").forward(request,response);
            //result = execute();
        }else {
            //获取class实例
            try {
                //利用反射来获取servlet对应的方法名称
                method = getServletClass().getDeclaredMethod(actiom,HttpServletRequest.class,HttpServletResponse.class);
                result = method.invoke(this,request,response);
                //System.out.println(result);
                //对结果进行处理
                toView(request,response,result);
            } catch (NoSuchMethodException e) {
                //请求的后台 没有找到
                String viewName ="404.jsp";
                request.getRequestDispatcher(viewName).forward(request,response);
                e.printStackTrace();
            }  catch (Exception e) {
                if (!EmptyUtils.isEmpty(result)){
                    if (result instanceof String){
                        String viewName = "500.jsp";
                        request.getRequestDispatcher(viewName).forward(request,response);

                    }else {
                        //服务器向客户端发送
                        ReturnResult returnResult = new ReturnResult();
                        returnResult.returnFail("系统错误");
                    }
                }else{
                    //如果为空的情况下
                    String viewName = "500.jsp";
                    request.getRequestDispatcher(viewName).forward(request,response);
                }
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * 进行对调用的方法进行处理的方法
     * @param request
     * @param response
     * @param result
     * @throws Exception
     */
    public  void toView(HttpServletRequest request,HttpServletResponse response,Object result) throws Exception{
        if (!EmptyUtils.isEmpty(result)){
            if (result instanceof  String){
                //如果返回结果为String就进行页面跳转；
                String viewName = result.toString() +".jsp";
                request.getRequestDispatcher(viewName).forward(request,response);
                //如果请求时只是需要一个数据的时候
            }else {
                write(result,response);
            }
        }
    }
    //跳转回首页
    public Object execute(){
        return "/show/index.jsp";
    }
    public void write(Object obj,HttpServletResponse response){
        //指定数据的展示方式
        response.setContentType("text/html;charset=utf-8");
        //转换为json数据  阿里巴巴
        String json = JSONObject.toJSONString(obj);
        //以流的形式返回给前台
        PrintWriter writer =null;
        if (null != response){
            try {
                writer = response.getWriter();
                writer.print(json);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                writer.close();
            }

        }
    }
}
