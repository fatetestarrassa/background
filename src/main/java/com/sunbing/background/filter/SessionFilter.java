package com.sunbing.background.filter;

import com.sunbing.background.entity.BackUser;
import com.sunbing.background.entity.BackUserContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * session过滤器，过滤web请求，判断登录状态
 *
 * @author sunbing
 * @version 1.0
 * @since 2020/12/9 17:51
 */
//@WebFilter(filterName = "sessionFilter",urlPatterns = "/*")
public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        //当前请求url
        String uri = request.getRequestURI();
        if(!needIntercept(uri)){
            //不需要拦截的uri
            chain.doFilter(servletRequest,servletResponse);
            return;
        }

        //非登录请求
        Object object = session.getAttribute("currentUser");
        if(object == null){
            response.sendRedirect("/toLoginPage");
            return;
        }
        //已登录：当前用户放入ThreadLocal中,方便使用
        BackUserContext.set((BackUser) object);
        chain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private boolean needIntercept(String uri){
        return !(uri.endsWith("login") || uri.endsWith("toLoginPage") ||
                uri.startsWith("/layui") || uri.startsWith("/css") || uri.startsWith("/js"));
    }
}
