package com.sunbing.background.service;

import com.sunbing.background.entity.ResponseResult;
import com.sunbing.background.param.LoginParam;

public interface LoginService {
    ResponseResult login(LoginParam param);
}
