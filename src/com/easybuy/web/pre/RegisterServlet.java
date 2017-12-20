package com.easybuy.web.pre;

import com.easybuy.entity.User;
import com.easybuy.service.user.UserService;
import com.easybuy.service.user.UserServiceImpl;
import com.easybuy.utils.*;
import com.easybuy.web.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Register",urlPatterns = {"/Register"})
public class RegisterServlet extends AbstractServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    public Class getServletClass() {
        return RegisterServlet.class;
    }

    /**
     * 跳转到注释页面
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String toRegister(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        return "/show/register";
    }

    /**
     * 注册页面
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public ReturnResult doRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("Register");
        ReturnResult result = new ReturnResult();
        try {
            User user = new User();
            String loginName = request.getParameter("loginName");
            String sex = request.getParameter("sex");
            User oldUser = userService.getUserByLoginName(loginName);
            //判断用户
            if (EmptyUtils.isNotEmpty(oldUser)) {
                result.returnFail("用户已经存在");
                return result;
            }
            user.setLoginName(request.getParameter("loginName"));
            user.setUserName(request.getParameter("userName"));
            user.setSex(EmptyUtils.isEmpty(sex) ? 1 : 0);
            user.setPassword(SecurityUtils.md5Hex(request.getParameter("password")));
            user.setIdentityCode(request.getParameter("identityCode"));
            user.setEmail(request.getParameter("email"));
            user.setMobile(request.getParameter("mobile"));
            user.setType(Constants.UserType.PRE);
            result=checkUser(user);
            //是否验证通过
            if(result.getStatus()==Constants.ReturnResult.SUCCESS){
                boolean flag=userService.save(user);
                if(!flag){
                    return result.returnFail("注册失败");
                }else{
                    return result.returnSuccess("注册成功");
                }
            }else{
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.returnSuccess("注册成功");
        return result;
    }

    /**
     * 对手机号，身份证号进行验证
     * @param user
     * @return
     */
    private ReturnResult checkUser(User user){
        ReturnResult result = new ReturnResult();
        boolean flag=true;
        if(EmptyUtils.isNotEmpty(user.getMobile())){
            if(!RegUtils.checkMobile(user.getMobile())){
                return result.returnFail("手机格式不正确");
            }
        }

        if(EmptyUtils.isNotEmpty(user.getIdentityCode())){
            if(!RegUtils.checkIdentityCodeReg(user.getIdentityCode())){
                return result.returnFail("身份证号码不正确");
            }
        }

        if(EmptyUtils.isNotEmpty(user.getEmail())){
            if(!RegUtils.checkEmail(user.getEmail())){
                return result.returnFail("邮箱格式不正确");
            }
        }
        return result.returnSuccess();
    }
}
