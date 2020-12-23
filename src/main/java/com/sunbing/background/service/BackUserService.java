package com.sunbing.background.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunbing.background.entity.BackUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunbing.background.entity.ResponseResult;
import com.sunbing.background.param.BackUserQueryParam;
import com.sunbing.background.param.BackUserSaveParam;
import com.sunbing.background.vo.BackUserPageVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author sunbing
 * @since 2020-11-23
 */
public interface BackUserService extends IService<BackUser> {
    IPage<BackUserPageVo> selectPageVo(Page page, BackUserQueryParam queryParam);
    BackUser getByUserNameAndPassword(String userName, String password);
    ResponseResult saveEntity(BackUserSaveParam param);
    ResponseResult updateEntity(BackUserSaveParam param);
    ResponseResult resetPwd(Integer id);
}
