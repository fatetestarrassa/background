package com.sunbing.background.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunbing
 * @version 1.0
 * @since 2020/12/22 17:42
 */
public class BaseController {
    public Integer getIdFromRequest(HttpServletRequest request){
        return Integer.parseInt(request.getParameter("id"));
    }
}
