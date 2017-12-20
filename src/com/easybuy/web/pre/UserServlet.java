package com.easybuy.web.pre;

import com.easybuy.entity.User;
import com.easybuy.service.user.UserService;
import com.easybuy.service.user.UserServiceImpl;
import com.easybuy.utils.EmptyUtils;
import com.easybuy.utils.ReturnResult;
import com.easybuy.utils.SecurityUtils;
import com.easybuy.web.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Login"},name = "Login")
public class UserServlet extends AbstractServlet {
    //注入用户业务类
    private UserService userService;
    @Override
    public Class getServletClass() {
        return UserServlet.class;
    }

    @Override
    public void init() throws ServletException {
        //初始化服务
        userService = new UserServiceImpl();
    }

    /**
     * 跳转到登录界面
     * @param request
     * @param response
     * @return
     * @throws ServletExceptiont
     * @throws IOException
     */
    public String gotoLogin(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        return "/show/Login";
    }
    public ReturnResult login(HttpServletRequest request,HttpServletResponse response) throws  Exception{
        ReturnResult returnResult = new ReturnResult();
        //参数的获取
        //获取账号
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        System.out.println(loginName + password);

        User user = userService.getUserByLoginName(loginName);
        if (EmptyUtils.isEmpty(user)){
            returnResult.returnFail("用户不存在");
        }else {
            //判断用户记录的密码
            //使用MD5进行加密
            if (user.getPassword().equals(SecurityUtils.md5Hex(password))){
                //把用户的信息存入到session中
                request.getSession().setAttribute("loginUser",user);
                returnResult.returnSuccess("登录成功");
            }else{
                returnResult.returnFail("密码不正确");
            }
        }
        //获取密码
        return returnResult;
    }
    public String loginOut(HttpServletRequest request,HttpServletResponse response) throws  Exception{
        ReturnResult returnResult = new ReturnResult();
        request.getSession().removeAttribute("loginUser");
        returnResult.returnSuccess("注销成功");
        return "/show/Login";
    }
}
