package com.sunbing.background.interceptor;

import com.sunbing.background.entity.BackUser;
import com.sunbing.background.entity.BackUserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * web拦截器，拦截请求，判断登录状态（与filter方式二选一）
 *
 * @author sunbing
 * @version 1.0
 * @since 2020/12/10 15:56
 */
@Slf4j
public class WebInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("preHandle .........uri = {}",request.getRequestURI());
        HttpSession session = request.getSession();
        Object object = session.getAttribute("currentUser");
        if(object == null){
            response.sendRedirect("/toLoginPage");
            return false;
        }
        BackUserContext.set((BackUser) object);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//        log.info("postHandle ............");
        request.setAttribute("currentUser",request.getSession().getAttribute("currentUser"));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
