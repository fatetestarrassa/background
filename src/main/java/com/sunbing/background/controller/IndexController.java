package com.sunbing.background.controller;

import com.sunbing.background.entity.BackUser;
import com.sunbing.background.entity.BackUserContext;
import com.sunbing.background.entity.ResponseResult;
import com.sunbing.background.param.LoginParam;
import com.sunbing.background.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author sunbing
 * @version 1.0
 * @since 2020/11/19 16:42
 */
@Controller
@Slf4j
public class IndexController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/index")
    public String index(HttpServletRequest request){
//        log.info("current userName is:{}", BackUserContext.get().getUserName());
        return "index";
    }

    @GetMapping("/toLoginPage")
    public String toLoginPage(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(HttpServletRequest request,LoginParam param){
        if(StringUtils.isBlank(param.getUserName())){
            return ResponseResult.Fail("用户名不能为空");
        }
        if(StringUtils.isBlank(param.getPassword())){
            return ResponseResult.Fail("密码不能为空");
        }

        return loginService.login(param);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("currentUser");
        return "login";
    }

}
