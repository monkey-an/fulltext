package com.fulltext.project.interceptor;

import com.fulltext.project.constants.ConstantValue;
import com.fulltext.project.entity.User;
import com.fulltext.project.entity.UserRole;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/9.
 *
 * @author anlu.
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User)request.getSession().getAttribute(ConstantValue.USER_SESSION_KEY);
        log.info("访问---"+request.getRequestURI());
        if (user == null || user.equals(""))  {
            response.sendRedirect("/user/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = (User)request.getSession().getAttribute(ConstantValue.USER_SESSION_KEY);
        UserRole userRole = (UserRole)request.getSession().getAttribute(ConstantValue.USER_ROLE_SESSION_KEY);
        if(modelAndView!=null) {
            modelAndView.addObject("user", user);
            modelAndView.addObject("userRole", userRole);
        }
    }
}
