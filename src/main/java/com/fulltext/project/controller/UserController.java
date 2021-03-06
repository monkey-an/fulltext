package com.fulltext.project.controller;

import com.fulltext.project.constants.ConstantValue;
import com.fulltext.project.entity.User;
import com.fulltext.project.entity.UserRole;
import com.fulltext.project.service.UserRoleService;
import com.fulltext.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/2/12.
 *
 * @author anlu.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    private static final int SESSION_AGE_SEVEN_DAYS = 7*24*3600;

    @RequestMapping("login")
    public String loginPage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute(ConstantValue.USER_SESSION_KEY)!=null){
            response.sendRedirect("/");
            return "hello";
        }else{
            return "login";
        }
    }

    @RequestMapping("doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request,HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        User user = userService.login(userName,password);
        if(user!=null){
            UserRole userRole = userRoleService.selectOneByUserId(user.getId());
            request.getSession().setAttribute(ConstantValue.USER_SESSION_KEY,user);
            request.getSession().setAttribute(ConstantValue.USER_ROLE_SESSION_KEY,userRole);

            if("true".equals(rememberMe)){
                request.getSession().setMaxInactiveInterval(SESSION_AGE_SEVEN_DAYS);
            }

            return "LOGIN_SUCCESS";
        }else{
            return "LOGIN_FAIL";
        }
    }

    @RequestMapping("logout")
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(ConstantValue.USER_SESSION_KEY);
        request.getSession().removeAttribute(ConstantValue.USER_ROLE_SESSION_KEY);
        request.getSession().setMaxInactiveInterval(-1);
        response.sendRedirect("/");
        return ;
    }

    @RequestMapping("regist")
    public String regist(){
        return "regist";
    }

    @RequestMapping("doRegist")
    public String doRegist(HttpServletRequest request,HttpServletResponse response){
        Map<String, String[]> parameterMap =  request.getParameterMap();
        String name = parameterMap.get("user-name-input")[0];
        String pwd = parameterMap.get("user-name-input")[0];
        String confirm_pwd = parameterMap.get("user-name-input")[0];
        String email = parameterMap.get("user-name-input")[0];
        String phone = parameterMap.get("user-name-input")[0];

        if(!pwd.equals(confirm_pwd)){
            return "输入密码不一致";
        }
        User entity = User.builder().loginName(name).password(pwd).email(email).phone(phone).build();
        if(userService.insert(entity) == 0){
            return "用户已经存在";
        }
        return "注册成功";
    }

}
