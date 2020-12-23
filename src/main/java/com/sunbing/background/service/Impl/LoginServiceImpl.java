package com.sunbing.background.service.Impl;

import com.sunbing.background.entity.BackUser;
import com.sunbing.background.entity.ResponseResult;
import com.sunbing.background.param.LoginParam;
import com.sunbing.background.service.BackUserService;
import com.sunbing.background.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登陆service实现类
 *
 * @author sunbing
 * @version 1.0
 * @since 2020/11/23 11:27
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private BackUserService backUserService;

    @Value("${login.salt}")
    private String salt;

    @Override
    public ResponseResult login(LoginParam param) {

        String encryptionPwd = DigestUtils.md5DigestAsHex((param.getPassword() + salt).getBytes());

        BackUser backUser = backUserService.getByUserNameAndPassword(param.getUserName(),encryptionPwd);
        if(backUser != null){
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("currentUser",backUser);
            return ResponseResult.Success(backUser);
        }

        return ResponseResult.Fail("用户名或者密码错误");
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex(("123456background").getBytes()));
    }
}
