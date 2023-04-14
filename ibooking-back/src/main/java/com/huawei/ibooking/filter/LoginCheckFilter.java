package com.huawei.ibooking.filter;

import com.huawei.ibooking.model.StudentDO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        log.info("开始跳转到登录页面");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String contextPath = ((HttpServletRequest) servletRequest).getContextPath();
        HttpSession httpSession = httpRequest.getSession();
        StudentDO studentDO = (StudentDO) httpSession.getAttribute("loginUser");
        String uri = String.valueOf(httpRequest.getRequestURL());

        if(null == studentDO && !uri.endsWith("login") && !uri.endsWith(".css") && !uri.endsWith(".js")&& !uri.endsWith(".png")&& !uri.endsWith(".gif")&& !uri.endsWith("loginInvalid")&& !uri.endsWith("hello")){
            //跳转到登录页面
            log.info(uri);
            httpResponse.sendRedirect(contextPath + "/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
